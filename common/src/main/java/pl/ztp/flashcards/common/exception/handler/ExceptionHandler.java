package pl.ztp.flashcards.common.exception.handler;

import pl.ztp.flashcards.common.dto.Response;

import java.util.Locale;

public interface ExceptionHandler {
    Class<? extends Exception> supportedException();

    Response prepareResponse(Throwable ex, Locale locale);
}
