package com.casanova.kas.test.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.casanova.kas.test")
@EnableConfigurationProperties
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
