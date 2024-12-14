package pl.ztp.flashcards.common.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pl.ztp.flashcards.common.entity.RolesEntity;
import reactor.core.publisher.Flux;

@Repository
public interface RolesRepository extends R2dbcRepository<RolesEntity, Long> {

    @Query("select * from roles where role_id in(select ur.role_id from users_roles ur where ur.user_id=:userId)")
    Flux<RolesEntity> findByUserId(Long userId);
}
