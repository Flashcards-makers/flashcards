package pl.ztp.flashcards.common.exception.handler;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.ztp.flashcards.common.dto.Response;
import pl.ztp.flashcards.common.i18n.MessagesEnum;
import pl.ztp.flashcards.common.i18n.Translator;

import java.util.Locale;

@Component
public class JWTExceptionHandler implements ExceptionHandler {

    public Class<? extends Exception> supportedException() {
        return JwtException.class;
    }

    public Response prepareResponse(Throwable ex, Locale local) {
        return new Response(Translator.translate(MessagesEnum.JWT_EXCEPTION, local), HttpStatus.UNAUTHORIZED);
    }
}
