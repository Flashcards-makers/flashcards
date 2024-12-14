package pl.ztp.flashcards.server.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pl.ztp.flashcards.server.entity.ResolvedEntity;

@Repository
public interface ResolvedRepository extends R2dbcRepository<ResolvedEntity, Long> {
}
