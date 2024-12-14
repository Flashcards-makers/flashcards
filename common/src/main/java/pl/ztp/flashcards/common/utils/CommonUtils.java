package pl.ztp.flashcards.common.utils;

import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;
import pl.ztp.flashcards.common.Constants;

@UtilityClass
public class CommonUtils {

    public static String getStringOrEmptyIfNull(String str) {
        return StringUtils.hasText(str) ? str : Constants.EMPTY_STRING;
    }
}
