package pl.ztp.flashcards.auth.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pl.ztp.flashcards.auth.entity.ActivateEntity;
import reactor.core.publisher.Mono;

@Repository
public interface ActivateRepository extends R2dbcRepository<ActivateEntity, Long> {


    Mono<ActivateEntity> findByCode(String code);
}
