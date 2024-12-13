package pl.ztp.flashcards.common.exception;

import lombok.Getter;
import pl.ztp.flashcards.common.i18n.MessagesEnum;

@Getter
public class NotFoundException extends RuntimeException {

    private final MessagesEnum messagesEnum;

    public NotFoundException(MessagesEnum messagesEnum) {
        super();
        this.messagesEnum = messagesEnum;
    }
}
