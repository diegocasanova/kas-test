package com.casanova.kas.test.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({"com.casanova.kas.test.api"})
public class WebConfig {
}
