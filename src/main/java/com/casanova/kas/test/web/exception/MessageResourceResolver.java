package com.casanova.kas.test.web.exception;


import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Optional;

@RequiredArgsConstructor
public class MessageResourceResolver {

    private final MessageSource messageSource;

    public String getMessage(final String messageProperty) {
        final Locale locale = Locale.ROOT;
        return Optional.ofNullable(messageProperty)
            .map(x -> messageSource.getMessage(messageProperty, null, locale))
            .orElse("");
    }
}
