package com.casanova.kas.test.spring.config;

import com.casanova.kas.test.rest.PackageClient;
import com.casanova.kas.test.rest.PackageRestClient;
import com.casanova.kas.test.rest.PackageClientRestImpl;
import com.casanova.kas.test.rest.mapper.PackageDesearializer;
import com.casanova.kas.test.rest.mapper.PackageResultDeserializer;
import com.casanova.kas.test.rest.model.Package;
import com.casanova.kas.test.rest.model.PackageResult;
import com.google.gson.GsonBuilder;
import lombok.Setter;
import lombok.val;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.validation.constraints.NotEmpty;

@Configuration
@ConfigurationProperties(prefix = "kas-test.packages.rest")
@Setter
public class RestClientConfig {

    @NotEmpty
    private String baseURL;

    @Bean
    public PackageRestClient packageRestClient() {
        val gson = new GsonBuilder()
            .registerTypeAdapter(Package.class, new PackageDesearializer())
            .registerTypeAdapter(PackageResult.class, new PackageResultDeserializer())
            .create();

        val retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

        return retrofit.create(PackageRestClient.class);
    }

    @Bean
    public PackageClient packageClient(final PackageRestClient packageRestClient) {
        return new PackageClientRestImpl(packageRestClient);
    }
}
