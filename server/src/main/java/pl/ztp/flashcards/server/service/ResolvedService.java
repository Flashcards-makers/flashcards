package pl.ztp.flashcards.server.service;

import pl.ztp.flashcards.common.mapper.MapFactory;
import pl.ztp.flashcards.common.utils.SecurityUtils;
import pl.ztp.flashcards.server.dto.request.SaveAttemptRequest;
import pl.ztp.flashcards.server.dto.response.FlashcardsListResponse;
import pl.ztp.flashcards.server.entity.ResolvedEntity;
import pl.ztp.flashcards.server.repository.ResolvedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ResolvedService {
    private final ResolvedRepository resolvedRepository;

    public Mono<ResolvedEntity> saveAttempt(SaveAttemptRequest request){
        return SecurityUtils.getLoggedUserId()
                .map(it -> {
                    ResolvedEntity entity = MapFactory.map(request, ResolvedEntity.class);
                    entity.setUserId(it);
                    return entity;
                }).flatMap(resolvedRepository::save);
    }
}