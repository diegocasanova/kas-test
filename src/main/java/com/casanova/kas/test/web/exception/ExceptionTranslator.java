package com.casanova.kas.test.web.exception;

import com.casanova.kas.test.web.exception.model.ApiError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionTranslator {

    private static final String API_ERROR_WITH_STATUS = "Api error: {} with status {} ";
    private final MessageResourceResolver messageResourceResolver;

    @ExceptionHandler(BaseApiException.class)
    public ResponseEntity<ApiError> processBaseApiException(final BaseApiException ex) {
        log.error("Handling BaseApiException: ", ex);
        final ApiError apiError = ex.toApiError(messageResourceResolver);
        final HttpStatus status = getResponseStatus(ex);
        log.debug(API_ERROR_WITH_STATUS, apiError, status);
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> processRuntimeException(final RuntimeException ex) {
        log.error("Handling RuntimeException: ", ex);
        final ApiError apiError = runtimeExceptionToApiError(ex);
        final HttpStatus status = getResponseStatus(ex);
        log.debug(API_ERROR_WITH_STATUS, apiError, status);
        return new ResponseEntity<>(apiError, status);
    }

    private ApiError runtimeExceptionToApiError(final Exception ex) {
        return new BaseApiException(ex.getMessage()).toApiError(messageResourceResolver);
    }


    private HttpStatus getResponseStatus(final Exception ex) {
        final ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        return Optional.ofNullable(responseStatus)
            .map(ResponseStatus::value)
            .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
