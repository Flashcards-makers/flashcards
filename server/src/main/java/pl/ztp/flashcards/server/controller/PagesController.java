package pl.ztp.flashcards.server.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import pl.ztp.flashcards.common.aop.Loggable;
import pl.ztp.flashcards.common.dto.UserInfoUserDetails;
import pl.ztp.flashcards.server.service.PagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pages")
public class PagesController {

    private final PagesService pagesService;

    @GetMapping("/{flashcardId}")
    @Loggable
    public Mono<ResponseEntity<?>> getPagesByFlashcardId(@PathVariable Long flashcardId, @AuthenticationPrincipal UserInfoUserDetails userDetails) {
        return pagesService.getPageByFlashcardId(flashcardId, userDetails).map(ResponseEntity::ok);
    }

    @GetMapping("/{flashcardId}/{previousPageId}")
    @Loggable
    public Mono<ResponseEntity<?>> getPagesByFlashcardIdAndPreviousPageId(@PathVariable Long flashcardId, @PathVariable Long previousPageId, @AuthenticationPrincipal UserInfoUserDetails userDetails) {
        return pagesService.getPageByFlashcardIdAndPreviousPageId(flashcardId, previousPageId, userDetails).map(ResponseEntity::ok);
    }

}
