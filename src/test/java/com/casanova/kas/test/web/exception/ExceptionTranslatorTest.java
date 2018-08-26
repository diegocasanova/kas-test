package com.casanova.kas.test.web.exception;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExceptionTranslatorTest {

    // Custom Messages
    private static final String PACKAGE_REST_EX_CODE = "REST-001";
    private static final String I18N_MESSAGE = "i18nMessage";
    private static final String PACKAGE_REST_EX_MSG = "rest.error";
    private static final String ERROR_MESSAGE = "custom.message";

    @Mock
    private MessageResourceResolver messageResourceResolver;
    private ExceptionTranslator exceptionTranslator;

    @Before
    public void init() {
        initMocks(this);
        exceptionTranslator = new ExceptionTranslator(messageResourceResolver);
    }


    @Test
    public void shouldHandleRuntimeException() {
        when(messageResourceResolver.getMessage(BaseApiException.DEFAULT_API_MESSAGE))
            .thenReturn(I18N_MESSAGE);
        val error =  exceptionTranslator.processRuntimeException(new RuntimeException(ERROR_MESSAGE));
        assertThat(error.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(error.getBody().getCode()).isEqualTo(BaseApiException.DEFAULT_API_ERROR);
        assertThat(error.getBody().getMessage()).isEqualTo(I18N_MESSAGE);
        verify(messageResourceResolver, times(1))
            .getMessage(BaseApiException.DEFAULT_API_MESSAGE);
        verifyNoMoreInteractions(messageResourceResolver);
    }


    @Test
    public void shouldHandlePackageRestException() {
        when(messageResourceResolver.getMessage(PACKAGE_REST_EX_MSG)).thenReturn(I18N_MESSAGE);
        val exception = new PackageRestException();
        val error = exceptionTranslator.processBaseApiException(exception);
        assertThat(error.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(error.getBody().getCode()).isEqualTo(PACKAGE_REST_EX_CODE);
        assertThat(error.getBody().getMessage()).isEqualTo(I18N_MESSAGE);
        verify(messageResourceResolver, times(1)).getMessage(PACKAGE_REST_EX_MSG);
        verifyNoMoreInteractions(messageResourceResolver);
    }

    @Test
    public void shouldHandleBaseApiExceptions() {
        when(messageResourceResolver.getMessage(BaseApiException.DEFAULT_API_MESSAGE)).thenReturn(I18N_MESSAGE);
        val exception = new BaseApiException(ERROR_MESSAGE);
        val error = exceptionTranslator.processBaseApiException(exception);
        assertThat(error.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(error.getBody().getCode()).isEqualTo(BaseApiException.DEFAULT_API_ERROR);
        assertThat(error.getBody().getMessage()).isEqualTo(I18N_MESSAGE);
        verify(messageResourceResolver, times(1))
            .getMessage(BaseApiException.DEFAULT_API_MESSAGE);
        verifyNoMoreInteractions(messageResourceResolver);
    }

}
