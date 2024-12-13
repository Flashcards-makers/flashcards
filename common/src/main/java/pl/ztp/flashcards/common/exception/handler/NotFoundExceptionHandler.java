package pl.ztp.flashcards.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.ztp.flashcards.common.dto.Response;
import pl.ztp.flashcards.common.exception.NotFoundException;
import pl.ztp.flashcards.common.i18n.MessagesEnum;
import pl.ztp.flashcards.common.i18n.Translator;

import java.util.Locale;

@Component
public class NotFoundExceptionHandler implements ExceptionHandler {

    public Class<? extends Exception> supportedException() {
        return NotFoundException.class;
    }

    public Response prepareResponse(Throwable ex, Locale local) {
        if (ex instanceof NotFoundException notFoundException) {
            return new Response(Translator.translate(notFoundException.getMessagesEnum(), local), HttpStatus.NOT_FOUND);
        } else {
            return new Response(Translator.translate(MessagesEnum.NOT_FOUND_EXCEPTION, local), HttpStatus.NOT_FOUND);
        }
    }
}
