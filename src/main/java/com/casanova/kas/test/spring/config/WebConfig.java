package com.casanova.kas.test.spring.config;

import com.casanova.kas.test.api.PackagesApiDelegate;
import com.casanova.kas.test.web.exception.MessageResourceResolver;
import com.casanova.kas.test.service.PackageService;
import com.casanova.kas.test.web.api.PackagesApiBackend;
import com.casanova.kas.test.web.mapper.PackageApiMapper;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.validation.constraints.NotEmpty;

@Configuration
@ConfigurationProperties(prefix = "kas-test.packages.api")
@ComponentScan(basePackages = "com.casanova.kas.test..web.exception")
@EnableCircuitBreaker
@Setter
public class WebConfig {

    @NotEmpty
    private String defaultLang;

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
        messageBundle.setBasenames("classpath:messages/messages", "classpath:messages/messagesBase");
        messageBundle.setDefaultEncoding("UTF-8");
        messageBundle.setUseCodeAsDefaultMessage(true);
        return messageBundle;
    }


    @Bean
    public MessageResourceResolver messageResourceResolver(final MessageSource messageSource) {
        return new MessageResourceResolver(messageSource);
    }

    @Bean
    public PackageApiMapper packageApiMapper() {
        return Mappers.getMapper(PackageApiMapper.class);
    }

    @Bean
    public PackagesApiDelegate packagesApiDelegate(final PackageApiMapper packageApiMapper,
                                                   final PackageService packageService) {
        return new PackagesApiBackend(packageService, packageApiMapper, defaultLang);
    }

}
