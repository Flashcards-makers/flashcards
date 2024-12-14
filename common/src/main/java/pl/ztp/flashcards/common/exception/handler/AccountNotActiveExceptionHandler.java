package pl.ztp.flashcards.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.ztp.flashcards.common.dto.Response;
import pl.ztp.flashcards.common.exception.AccountNotActiveException;
import pl.ztp.flashcards.common.i18n.MessagesEnum;
import pl.ztp.flashcards.common.i18n.Translator;

import java.util.Locale;

@Component
public class AccountNotActiveExceptionHandler implements ExceptionHandler {

    public Class<? extends Exception> supportedException() {
        return AccountNotActiveException.class;
    }

    public Response prepareResponse(Throwable ex, Locale local) {
        if (ex instanceof AccountNotActiveException exception) {
            return new Response(Translator.translate(exception.getMessagesEnum(), local), HttpStatus.UNAUTHORIZED);
        } else {
            return new Response(Translator.translate(MessagesEnum.ACTIVATE_ACCOUNT_EXCEPTION, local), HttpStatus.UNAUTHORIZED);
        }
    }
}
