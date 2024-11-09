package pl.ztp.flashcards.common.exception;

import pl.ztp.flashcards.common.i18n.MessagesEnum;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class BadCredentialsException extends RuntimeException {

    private final MessagesEnum messagesEnum;
    public BadCredentialsException(MessagesEnum messagesEnum) {
        super();
        this.messagesEnum = messagesEnum;
    }
    public BadCredentialsException() {
        super();
        this.messagesEnum = MessagesEnum.BAD_CREDENTIALS_EXCEPTION;
    }
}
