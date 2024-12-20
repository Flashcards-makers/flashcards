package pl.ztp.flashcards.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ztp.flashcards.auth.dto.request.*;
import pl.ztp.flashcards.auth.services.AuthService;
import pl.ztp.flashcards.common.aop.Loggable;
import pl.ztp.flashcards.common.dto.Response;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/test")
    public Mono<String> test() {
        return Mono.just("Hello");
    }

    @PostMapping("/login")
    @Loggable
    public Mono<ResponseEntity<?>> login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh-token")
    @Loggable
    public Mono<ResponseEntity<?>> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest).map(it -> new Response(it, HttpStatus.OK));
    }

    @PostMapping("/register")
    @Loggable
    public Mono<ResponseEntity<?>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.saveUser(registerRequest)
                .map(u -> new Response("User registered!", HttpStatus.CREATED));
    }

    @PatchMapping("/register/activation")
    @Loggable
    public Mono<ResponseEntity<?>> registerActivation(@Valid @RequestBody RegisterAcceptRequest registerRequest) {
        return authService.registerAccept(registerRequest)
                .map(it -> new Response(HttpStatus.CREATED));
    }

    @PutMapping("/register/user-name/valid")
    @Loggable
    public Mono<ResponseEntity<?>> userNameValid(@Valid @RequestBody UserNameRequest registerRequest) {
        return Mono.just(new Response(HttpStatus.OK));
    }
}
