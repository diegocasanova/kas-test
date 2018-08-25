package com.casanova.kas.test.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LanguageNotSupportedException extends BaseApiException {

    private static final String API_CODE = "LANG-001";
    private static final String API_MESSAGE = "lang.not.supported";
    private static final String ERROR_MESSAGE = "TThe language {} is not supported";

    public LanguageNotSupportedException(final String lang) {
        super(String.format(ERROR_MESSAGE, lang), null, API_CODE, API_MESSAGE);
    }
}
