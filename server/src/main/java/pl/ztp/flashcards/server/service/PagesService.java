package pl.ztp.flashcards.server.service;

import pl.ztp.flashcards.common.mapper.MapFactory;
import pl.ztp.flashcards.common.utils.SecurityUtils;
import pl.ztp.flashcards.server.dto.response.PagesListResponse;
import pl.ztp.flashcards.server.entity.PagesEntity;
import pl.ztp.flashcards.server.repository.PagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PagesService {
    private final PagesRepository pagesRepository;

    public Mono<PagesEntity> getPageByFlashcardId(Long flashcardId) {
        return SecurityUtils.getLoggedUserId()
                .flatMap(id -> pagesRepository.findFirstByFlashcardsIdAndUserId(flashcardId, id));

    }
    public Mono<PagesEntity> getPageByFlashcardIdAndPreviousPageId(Long flashcardId, Long previousPageId) {
        return SecurityUtils.getLoggedUserId()
                .flatMap(id -> pagesRepository.findByFlashcardsIdAndPreviousPageId(flashcardId, id, previousPageId))
                .switchIfEmpty(getPageByFlashcardId(flashcardId));
    }
}
