package pl.ztp.flashcards.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ztp.flashcards.common.aop.Loggable;
import pl.ztp.flashcards.common.dto.UserInfoUserDetails;
import pl.ztp.flashcards.server.dto.request.SaveAttemptRequest;
import pl.ztp.flashcards.server.service.ResolvedService;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resolve")
public class ResolvedController {
    private final ResolvedService resolvedService;

    @PostMapping
    @Loggable
    public Mono<ResponseEntity<?>> saveAttempt(@RequestBody SaveAttemptRequest saveAttemptRequest, @AuthenticationPrincipal UserInfoUserDetails userDetails) {
        return resolvedService.saveAttempt(saveAttemptRequest, userDetails).map(resolvedEntity -> {
            if (Objects.nonNull(resolvedEntity)) {
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.internalServerError().build();
        });
    }
}
