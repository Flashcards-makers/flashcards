package pl.ztp.flashcards.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.ztp.flashcards.common.dto.Response;
import pl.ztp.flashcards.common.exception.MapperNotFoundException;
import pl.ztp.flashcards.common.i18n.MessagesEnum;
import pl.ztp.flashcards.common.i18n.Translator;

import java.util.Locale;

@Component
public class MapperNotFoundExceptionHandler implements ExceptionHandler {

    public Class<? extends Exception> supportedException() {
        return MapperNotFoundException.class;
    }

    public Response prepareResponse(Throwable ex, Locale local) {
        return new Response(Translator.translate(MessagesEnum.UNEXPECTED_ERROR, local), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
