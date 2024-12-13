package pl.ztp.flashcards.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ztp.flashcards.auth.amqp.RabbitMQProducer;
import pl.ztp.flashcards.auth.dto.request.LoginRequest;
import pl.ztp.flashcards.auth.dto.request.RefreshTokenRequest;
import pl.ztp.flashcards.auth.dto.request.RegisterAcceptRequest;
import pl.ztp.flashcards.auth.dto.request.RegisterRequest;
import pl.ztp.flashcards.auth.dto.response.AuthResponse;
import pl.ztp.flashcards.auth.entity.ActivateEntity;
import pl.ztp.flashcards.auth.mapper.RegisterRequestToUsersEntityMapper;
import pl.ztp.flashcards.auth.repository.ActivateRepository;
import pl.ztp.flashcards.common.dto.Response;
import pl.ztp.flashcards.common.entity.UsersEntity;
import pl.ztp.flashcards.common.exception.AccountNotActiveException;
import pl.ztp.flashcards.common.exception.BadCredentialsException;
import pl.ztp.flashcards.common.exception.ExpiredException;
import pl.ztp.flashcards.common.exception.NotFoundException;
import pl.ztp.flashcards.common.i18n.MessagesEnum;
import pl.ztp.flashcards.common.repository.UsersRepository;
import pl.ztp.flashcards.common.service.JWTService;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final ActivateRepository activateRepository;
    private final RabbitMQProducer rabbitMQProducer;


    public Mono<ResponseEntity<?>> login(LoginRequest loginRequest) {
        return usersRepository.findByEmail(loginRequest.email())
                .switchIfEmpty(Mono.error(new NotFoundException(MessagesEnum.NOT_FOUND_EXCEPTION)))
                .filter(it -> passwordEncoder.matches(loginRequest.password(), it.getPassword()))
                .switchIfEmpty(Mono.error(new BadCredentialsException(MessagesEnum.NOT_FOUND_EXCEPTION)))
                .publishOn(Schedulers.boundedElastic())
                .filter(it -> Boolean.TRUE.equals(activateRepository.findById(it.getActivateId())
                        .map(ActivateEntity::getIsActivated)
                        .filter(bool -> bool)
                        .block()))
                .switchIfEmpty(Mono.error(new AccountNotActiveException(MessagesEnum.ACTIVATE_ACCOUNT_EXCEPTION)))
                .map(usersEntity -> {
                    usersEntity.createRefreshToken();
                    return usersEntity;
                })
                .flatMap(usersRepository::save)
                .map(usersEntity -> new Response(AuthResponse.builder()
                        .name(usersEntity.getUserName())
                        .surname(usersEntity.getSurname())
                        .userName(usersEntity.getUserName())
                        .jwtToken(jwtService.generateToken(usersEntity.getEmail()))
                        .refreshToken(usersEntity.getRefreshToken())
                        .build(), HttpStatus.OK));
    }

    public Mono<UsersEntity> saveUser(RegisterRequest registerRequest) {
        return usersRepository.save(RegisterRequestToUsersEntityMapper.INSTANCE.sourceToDestination(encodePassword(registerRequest)))
                .doOnNext(rabbitMQProducer::sendMessage);
    }

    public Mono<ActivateEntity> registerAccept(RegisterAcceptRequest request) {
        return activateRepository.findByCode(request.uid())
                .switchIfEmpty(Mono.error(new NotFoundException(MessagesEnum.ACTIVATE_NOT_FOUND_EXCEPTION)))
                .filter(activateEntity -> activateEntity.getExpiredAt().isAfter(LocalDateTime.now()))
                .switchIfEmpty(Mono.error(new ExpiredException()))
                .flatMap(it -> activateRepository.save(it.activate()));
    }

    public Mono<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return usersRepository.findByEmail(refreshTokenRequest.email())
                .switchIfEmpty(Mono.error(new NotFoundException(MessagesEnum.NOT_FOUND_EXCEPTION)))
                .filter(entity -> entity.getRefreshTokenExpiration().isAfter(LocalDateTime.now()))
                .switchIfEmpty(Mono.error(new ExpiredException(MessagesEnum.NOT_FOUND_EXCEPTION)))
                .flatMap(createRefreshToken())
                .map(createJwtToken());
    }

    private Function<UsersEntity, AuthResponse> createJwtToken() {
        return it -> AuthResponse.builder()
                .name(it.getName())
                .surname(it.getSurname())
                .userName(it.getUserName())
                .jwtToken(jwtService.generateToken(it.getEmail()))
                .refreshToken(it.getRefreshToken())
                .build();
    }


    private Function<UsersEntity, Mono<? extends UsersEntity>> createRefreshToken() {
        return it -> {
            it.createRefreshToken();
            return usersRepository.save(it);
        };
    }


    private RegisterRequest encodePassword(RegisterRequest registerRequest) {
        String encryptedPassword = passwordEncoder.encode(registerRequest.password());
        return new RegisterRequest(registerRequest.name(), registerRequest.surname(),
                registerRequest.userName(), encryptedPassword, registerRequest.email());
    }
}