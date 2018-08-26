package com.casanova.kas.test.rest;

import com.casanova.kas.test.rest.model.PackageResult;
import com.casanova.kas.test.web.exception.PackageRestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@AllArgsConstructor
@Slf4j
public class PackageRestClientImpl implements PackageClient {

    private static final String ERROR_MSG = "unsuccessful response: {}, {}";
    private static final String NO_SUCCESS_MSG = "Package Rest Response unsuccessful for query: {}, " +
        "limit: {}, offset: {}";
    private final PackageRestClient packageRestClient;

    @Override
    public PackageResult getPackages(final Integer limit, final Integer offset, final String query) {
        try {
            Call<PackageResult> call = packageRestClient.listPackages(query, limit, offset);
            Response<PackageResult> response = call.execute();
            PackageResult result = response.body();
            if (!response.isSuccessful()){
                throw new PackageRestException(String.format(ERROR_MSG, response.code(), response.message()));
            }
            if (!result.isSuccess()){
                log.debug(String.format(NO_SUCCESS_MSG, query, limit, offset));
            }
            return result;
        } catch (IOException ex) {
            throw new PackageRestException(ex);
        }
    }

}
