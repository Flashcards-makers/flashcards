package pl.ztp.flashcards.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;
import pl.ztp.flashcards.common.dto.Response;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class WebExchangeBindExceptionHandler implements ExceptionHandler {
    @Override
    public Class<? extends Exception> supportedException() {
        return WebExchangeBindException.class;
    }

    @Override
    public Response prepareResponse(Throwable ex, Locale locale) {
        Map<String, String> errors = new HashMap<>();

        if (ex instanceof WebExchangeBindException e) {
            e.getAllErrors().forEach(objectError -> errors.put(((FieldError) objectError).getField(), objectError.getDefaultMessage()));
        }

        return new Response(errors, HttpStatus.BAD_REQUEST);
    }
}
