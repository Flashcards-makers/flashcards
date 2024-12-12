package pl.ztp.flashcards.server.repository;

import pl.ztp.flashcards.server.entity.ResolvedEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResolvedRepository extends R2dbcRepository<ResolvedEntity, Long> {
}
