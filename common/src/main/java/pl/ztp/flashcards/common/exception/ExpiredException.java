package pl.ztp.flashcards.common.exception;

import pl.ztp.flashcards.common.i18n.MessagesEnum;
import lombok.Getter;

@Getter
public class ExpiredException extends RuntimeException{

    private final MessagesEnum messagesEnum;
    public ExpiredException(MessagesEnum messagesEnum) {
        super();
        this.messagesEnum = messagesEnum;
    }
    public ExpiredException() {
        super();
        this.messagesEnum = MessagesEnum.LINK_EXPIRED_EXCEPTION;
    }
}
