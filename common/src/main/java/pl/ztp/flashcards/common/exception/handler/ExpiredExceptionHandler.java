package pl.ztp.flashcards.common.exception.handler;

import pl.ztp.flashcards.common.dto.Response;
import pl.ztp.flashcards.common.exception.ExpiredException;
import pl.ztp.flashcards.common.i18n.MessagesEnum;
import pl.ztp.flashcards.common.i18n.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ExpiredExceptionHandler implements ExceptionHandler {

    public Class<? extends Exception> supportedException() {
        return ExpiredException.class;
    }

    public Response prepareResponse(Throwable ex, Locale local) {
        if (ex instanceof ExpiredException notFoundException) {
            return new Response(Translator.translate(notFoundException.getMessagesEnum(), local), HttpStatus.GONE);
        }else {
            return new Response(Translator.translate(MessagesEnum.LINK_EXPIRED_EXCEPTION, local), HttpStatus.GONE);
        }
    }
}
