package pl.ztp.flashcards.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ztp.flashcards.common.entity.RolesEntity;
import pl.ztp.flashcards.common.entity.UsersEntity;
import pl.ztp.flashcards.common.repository.RolesRepository;
import pl.ztp.flashcards.common.repository.UsersRepository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    Mono<UsersEntity> findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .publishOn(Schedulers.boundedElastic())
                .map(usersEntity -> {
                    List<RolesEntity> rolesList = rolesRepository.findByUserId(usersEntity.getId()).collectList().block();
                    usersEntity.setRoles(rolesList);
                    return usersEntity;
                });
    }
}
