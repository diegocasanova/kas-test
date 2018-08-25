package com.casanova.kas.test.spring.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty("swagger.enabled")
@ComponentScan({"org.openapitools.configuration"})
public class ApiConfig {
}
