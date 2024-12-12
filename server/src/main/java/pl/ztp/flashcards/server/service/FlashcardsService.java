package pl.ztp.flashcards.server.service;

import pl.ztp.flashcards.common.mapper.MapFactory;
import pl.ztp.flashcards.common.repository.UsersRepository;
import pl.ztp.flashcards.common.utils.SecurityUtils;
import pl.ztp.flashcards.server.dto.request.SaveFlashardsRequest;
import pl.ztp.flashcards.server.dto.response.FlashcardsListResponse;
import pl.ztp.flashcards.server.entity.FlashcardsEntity;
import pl.ztp.flashcards.server.entity.PagesEntity;
import pl.ztp.flashcards.server.repository.FlashcardsRepository;
import pl.ztp.flashcards.server.repository.PagesRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class FlashcardsService {

    private final FlashcardsRepository flashcardsRepository;
    private final PagesRepository pagesRepository;
    private final UsersRepository usersRepository;

    private static Flux<PagesEntity> getPagesEntityFlux(Mono<FlashcardsEntity> flashcardsEntity) {
        return flashcardsEntity
                .map(flashcards -> {
                            flashcards
                                    .getPages()
                                    .forEach(page -> page.setFlashcardId(flashcards.getId()));
                            return flashcards.getPages();
                        }
                )
                .flatMapMany(Flux::fromIterable);
    }

    @Transactional
    public Mono<Boolean> saveFlashcards(SaveFlashardsRequest request) {
        Mono<FlashcardsEntity> flashcardsEntity = saveFlashcardsEntity(request);
        Flux<PagesEntity> pagesEntity = savePagesEntity(flashcardsEntity);
        return pagesEntity.collectList().hasElement();
    }

    public Flux<FlashcardsListResponse> getFlashcards(String name) {
        return SecurityUtils.getLoggedUserId()
                .flatMapMany(id -> this.findAllByUserAndName(id, name))
                .map(flashcardsEntity -> MapFactory.map(flashcardsEntity, FlashcardsListResponse.class))
                .publishOn(Schedulers.boundedElastic())
                .map(flashcardsListResponse -> {
                    String userName = usersRepository.getUserNameById(Long.valueOf(flashcardsListResponse.getCreatedBy())).block();
                    flashcardsListResponse.setCreatedBy(userName);
                    return flashcardsListResponse;
                });
    }

    private Flux<FlashcardsEntity> findAllByUserAndName(Long id, String name) {
        if (StringUtils.isEmpty(name)) {
            return flashcardsRepository.findAllByUser(id);
        } else {
            return flashcardsRepository.findAllByUserAndName(id, name);
        }
    }

    private Flux<PagesEntity> savePagesEntity(Mono<FlashcardsEntity> flashcardsEntity) {
        return pagesRepository.saveAll(getPagesEntityFlux(flashcardsEntity));
    }

    private Mono<FlashcardsEntity> saveFlashcardsEntity(SaveFlashardsRequest request) {
        return SecurityUtils.getLoggedUserId().map(id -> {
                    FlashcardsEntity entity = MapFactory.map(request, FlashcardsEntity.class);
                    entity.setCreatedBy(id);
                    return entity;
                })
                .flatMap(flashcardsRepository::save);
    }

}
