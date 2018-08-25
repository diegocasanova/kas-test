package com.casanova.kas.test.web.exception;

import com.casanova.kas.test.web.exception.model.ApiError;
import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseApiExceptionTest {

    private static final String EXPECTED_MESSAGE = "expected";

    @Test
    public void shouldCheckBuildsExceptionWithMessage() {
        val msg = "This is a message";
        val exception = new BaseApiException(msg);
        assertThat(exception).isNotNull().hasMessage(msg).hasNoCause();
    }

    @Test
    public void shouldCheckBuildsExceptionWithMessageAndCause() {
        val msg = "This is a message";
        val cause = new Exception("cause");
        val exception = new BaseApiException(msg, cause);
        assertThat(exception).isNotNull().hasMessage(msg).hasCause(cause);
    }

    @Test
    public void shouldTranslateToApiError() {
        val message = "message";
        val cause = new RuntimeException("Boom!");
        val apiCode = "api.code";
        val apiMessage = "api.message";

        final MessageResourceResolver messageResourceResolver = mock(MessageResourceResolver.class);
        when(messageResourceResolver.getMessage(apiMessage)).thenReturn(EXPECTED_MESSAGE);
        final BaseApiException exception = new BaseApiException(message, cause, apiCode, apiMessage);
        final ApiError apiError = exception.toApiError(messageResourceResolver);

        assertThat(apiError.getCode()).isEqualTo(apiCode);
        assertThat(apiError.getMessage()).isEqualTo(EXPECTED_MESSAGE);

    }
}
