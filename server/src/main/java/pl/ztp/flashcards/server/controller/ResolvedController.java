package pl.ztp.flashcards.server.controller;

import pl.ztp.flashcards.common.aop.Loggable;
import pl.ztp.flashcards.server.dto.request.SaveAttemptRequest;
import pl.ztp.flashcards.server.service.ResolvedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resolve")
public class ResolvedController {
    private final ResolvedService resolvedService;
    @PostMapping
    @Loggable
    public Mono<ResponseEntity<?>> saveAttempt(@RequestBody SaveAttemptRequest saveAttemptRequest) {
        return resolvedService.saveAttempt(saveAttemptRequest).map(resolvedEntity -> {
            if (Objects.nonNull(resolvedEntity)){
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.internalServerError().build();
        });
    }
}
