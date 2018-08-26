package com.casanova.kas.test.rest;

import com.casanova.kas.test.rest.model.PackageResult;
import com.casanova.kas.test.web.exception.PackageRestException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@AllArgsConstructor
@Slf4j
public class PackageClientRestImpl implements PackageClient {

    private static final String ERROR_MSG = "unsuccessful response: {}, {}";
    private static final String NO_SUCCESS_MSG =
        "Package Rest Response unsuccessful for query: {}, limit: {}, offset: {}";
    private final PackageRestClient packageRestClient;

    @HystrixCommand(fallbackMethod = "fallbackGetPackages", commandProperties = {
        @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
    })
    @Override
    public PackageResult getPackages(final Integer limit, final Integer offset, final String query) {
        Call<PackageResult> call = packageRestClient.listPackages(query, limit, offset);
        Response<PackageResult> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new PackageRestException(e);
        }
        val result = response.body();
        if (!response.isSuccessful()) {
            throw new PackageRestException(String.format(ERROR_MSG, response.code(), response.message()));
        }
        if (!result.isSuccess()) {
            log.debug(String.format(NO_SUCCESS_MSG, query, limit, offset));
        }
        return result;
    }

    public PackageResult fallbackGetPackages(final Integer limit, final Integer offset, final String query) {
        log.warn("Something went wrong!!! Executing the fallback method to obtain Packages.");
        return PackageResult.builder()
            .success(false)
            .count(0)
            .build();
    }

}
