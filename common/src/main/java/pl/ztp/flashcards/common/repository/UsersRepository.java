package pl.ztp.flashcards.common.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ztp.flashcards.common.entity.UsersEntity;
import reactor.core.publisher.Mono;

@Repository
public interface UsersRepository extends R2dbcRepository<UsersEntity, Long> {

    Mono<UsersEntity> findByEmail(String email);

    @Query("SELECT u.user_name FROM users u where u.user_id = :userId")
    Mono<String> getUserNameById(@Param("userId") Long userId);

    Mono<Boolean> existsByUserName(String userName);
}
