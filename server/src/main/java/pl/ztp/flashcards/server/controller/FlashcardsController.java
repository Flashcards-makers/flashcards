package pl.ztp.flashcards.server.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import pl.ztp.flashcards.common.aop.Loggable;
import pl.ztp.flashcards.common.dto.UserInfoUserDetails;
import pl.ztp.flashcards.server.dto.request.SaveFlashardsRequest;
import pl.ztp.flashcards.server.service.FlashcardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class FlashcardsController {

    private final FlashcardsService flashcardsService;

    @Loggable
    @PostMapping
    public Mono<ResponseEntity<?>> saveFlashcards(@RequestBody Mono<SaveFlashardsRequest> request, @AuthenticationPrincipal UserInfoUserDetails userDetails) {
        return request.flatMap(it ->flashcardsService.saveFlashcards(it, userDetails)).map(it -> {
            if(Boolean.TRUE.equals(it)){
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.internalServerError().build();
        });
    }

    @Loggable
    @GetMapping("/list")
    public Mono<ResponseEntity<?>> getFlashcards(@RequestParam(name = "name", required = false) String name, @AuthenticationPrincipal UserInfoUserDetails userDetails) {
        return flashcardsService.getFlashcards(name, userDetails).collectList().map(ResponseEntity::ok);
    }
}
