package pl.ztp.flashcards.server.service;

import pl.ztp.flashcards.common.dto.UserInfoUserDetails;
import pl.ztp.flashcards.server.entity.PagesEntity;
import pl.ztp.flashcards.server.repository.PagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PagesService {
    private final PagesRepository pagesRepository;

    public Mono<PagesEntity> getPageByFlashcardId(Long flashcardId, UserInfoUserDetails userDetails) {
        return Mono.just(userDetails.getId())
                .flatMap(id -> pagesRepository.findFirstByFlashcardsIdAndUserId(flashcardId, id));

    }
    public Mono<PagesEntity> getPageByFlashcardIdAndPreviousPageId(Long flashcardId, Long previousPageId, UserInfoUserDetails userDetails) {
        return Mono.just(userDetails.getId())
                .flatMap(id -> pagesRepository.findByFlashcardsIdAndPreviousPageId(flashcardId, id, previousPageId))
                .switchIfEmpty(getPageByFlashcardId(flashcardId, userDetails));
    }
}
