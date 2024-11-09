package pl.ztp.flashcards.common.utils;

import pl.ztp.flashcards.common.dto.UserInfoUserDetails;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.core.publisher.Mono;

@UtilityClass
public class SecurityUtils {

    public static Mono<UserInfoUserDetails> getLoggedUser() {
        return ReactiveSecurityContextHolder.getContext().map(it -> it.getAuthentication().getPrincipal())
                .cast(UserInfoUserDetails.class);
    }

    public static Mono<Long> getLoggedUserId() {
        return getLoggedUser().map(UserInfoUserDetails::getId);
    }

    public static Mono<String> getLoggedUserEmail() {
        return getLoggedUser().map(UserInfoUserDetails::getEmail);
    }
}
