package pl.ztp.flashcards.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ztp.flashcards.common.dto.UserInfoUserDetails;
import pl.ztp.flashcards.common.mapper.MapFactory;
import pl.ztp.flashcards.server.dto.request.SaveAttemptRequest;
import pl.ztp.flashcards.server.entity.ResolvedEntity;
import pl.ztp.flashcards.server.repository.ResolvedRepository;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ResolvedService {
    private final ResolvedRepository resolvedRepository;

    public Mono<ResolvedEntity> saveAttempt(SaveAttemptRequest request, UserInfoUserDetails userDetails) {
        ResolvedEntity entity = MapFactory.map(request, ResolvedEntity.class);
        entity.setUserId(userDetails.getId());
        return resolvedRepository.save(entity);
    }
}
