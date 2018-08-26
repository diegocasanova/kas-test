package com.casanova.kas.test.web.exception;

import com.casanova.kas.test.web.exception.model.ApiError;

import java.util.Optional;

public class BaseApiException extends RuntimeException {

    public static final String DEFAULT_API_ERROR = "API-000";
    public static final String DEFAULT_API_MESSAGE = "error.body.default";
    private static final long serialVersionUID = 2329341531944068953L;
    private final String apiCode;
    private final String apiMessage;

    public BaseApiException(final String message, final Throwable cause, final String apiCode,
                            final String apiMessage) {
        super(message, cause);
        this.apiCode = Optional.ofNullable(apiCode).orElse(DEFAULT_API_ERROR);
        this.apiMessage = Optional.ofNullable(apiMessage).orElse(DEFAULT_API_MESSAGE);
    }

    public BaseApiException(final String message) {
        this(message, null, null, null);
    }

    public BaseApiException(final String message, final Throwable cause) {
        this(message, cause, null, null);
    }


    public ApiError toApiError(final MessageResourceResolver messageResourceResolver) {
        final String i18nMessage = getMessageFromBaseApiException(messageResourceResolver);
        return ApiError.builder()
            .code(apiCode)
            .message(i18nMessage)
            .build();
    }

    private String getMessageFromBaseApiException(final MessageResourceResolver messageResourceResolver) {
        return messageResourceResolver.getMessage(apiMessage);
    }
}
