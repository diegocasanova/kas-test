package com.casanova.kas.test.web.exception;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MessageResourceResolverTest {

    private static final String EXPECTED_MESSAGE = "expected message.";

    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private MessageResourceResolver messageResolver;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldGetEmptyMessageWhenNullProperty() {
        final String property = null;
        initMockMessageSource(property);
        final String message = messageResolver.getMessage(property);
        assertThat(message).isNotNull().isEmpty();
        verify(messageSource, never()).getMessage(anyString(), any(), any(Locale.class));
    }

    @Test
    public void shouldGetMessageWhenPassPropertyAndArguments() {
        val property = "codigo.propiedad";
        initMockMessageSource(property);
        final String msg = messageResolver.getMessage(property);
        assertThat(msg).isNotNull().isEqualTo(EXPECTED_MESSAGE);
        verify(messageSource, times(1)).getMessage(property, null, Locale.ROOT);
    }

    private void initMockMessageSource(final String propiead) {
        when(messageSource.getMessage(propiead, null, Locale.ROOT)).thenReturn(EXPECTED_MESSAGE);
    }


}
