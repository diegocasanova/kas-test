package com.casanova.kas.test.spring.config;

import com.casanova.kas.test.rest.PackageClient;
import com.casanova.kas.test.service.PackageService;
import com.casanova.kas.test.service.PackageServiceImpl;
import com.casanova.kas.test.service.mapper.PackageDTOMapper;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties(prefix = "kas-test.packages.service")
@Setter
public class ServiceConfig {

    @NotNull
    private Integer defaultOffset;

    @NotNull
    private Integer defaultLimit;

    @Bean
    public PackageDTOMapper packageToDTOMapper() {
        return Mappers.getMapper(PackageDTOMapper.class);
    }

    @Bean
    public PackageService packageRestService(final PackageDTOMapper packageDTOMapper,
                                         final PackageClient packageClient) {
        return new PackageServiceImpl(packageDTOMapper, packageClient, defaultOffset, defaultLimit);
    }
}
