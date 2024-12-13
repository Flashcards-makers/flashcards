package pl.ztp.flashcards.common.exception;

import lombok.Getter;
import pl.ztp.flashcards.common.i18n.MessagesEnum;

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
