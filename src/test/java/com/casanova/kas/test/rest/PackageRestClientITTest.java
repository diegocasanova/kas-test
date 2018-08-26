package com.casanova.kas.test.rest;

import com.casanova.kas.test.spring.config.RestClientConfig;
import com.casanova.kas.test.spring.config.WebConfig;
import com.casanova.kas.test.web.exception.PackageRestException;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {RestClientConfig.class, PackageRestClientITTest.DummyBootApplication.class})
@AutoConfigureWireMock(port = 0, files = "classpath:/wiremock")
public class PackageRestClientITTest {

    @Autowired
    private PackageClient packageClient;


    @Test
    public void shouldReturnAValidPackageResult() {
        val packages = packageClient.getPackages(10, 10, "test");
        assertThat(packages).isNotNull();
        assertThat(packages.getCount()).isEqualTo(2);
        assertThat(packages.getPackages()).hasSize(2);
        val first = packages.getPackages().get(0);
        assertThat(first.getCode()).isEqualTo("CATALEG_OPENDATA");
        assertThat(first.getOrganizationDescription()).isEqualTo("Public sector");
        assertThat(first.getUrlByLanguage("ca")).isEqualTo("http://opendata-ajuntament.barcelona.cat/");
    }

    @Test
    public void shouldExecuteTheFallbackMethodWhenResponseNotSuccessful() {
        val packages = packageClient.getPackages(10, 10, "error");
        assertThat(packages).isNotNull();
        assertThat(packages.isSuccess()).isFalse();
    }


    @SpringBootApplication
    @EnableCircuitBreaker
    static class DummyBootApplication {

    }

}
