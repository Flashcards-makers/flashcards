package pl.ztp.flashcards.common.exception;

import lombok.Getter;
import pl.ztp.flashcards.common.i18n.MessagesEnum;

@Getter
public class AccountNotActiveException extends RuntimeException {

    private final MessagesEnum messagesEnum;

    public AccountNotActiveException(MessagesEnum messagesEnum) {
        super();
        this.messagesEnum = messagesEnum;
    }

    public AccountNotActiveException() {
        super();
        this.messagesEnum = MessagesEnum.LINK_EXPIRED_EXCEPTION;
    }
}
