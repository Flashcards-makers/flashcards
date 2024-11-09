package pl.ztp.flashcards.common.exception;

import pl.ztp.flashcards.common.i18n.MessagesEnum;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{

    private final MessagesEnum messagesEnum;
    public NotFoundException(MessagesEnum messagesEnum) {
        super();
        this.messagesEnum = messagesEnum;
    }
}
