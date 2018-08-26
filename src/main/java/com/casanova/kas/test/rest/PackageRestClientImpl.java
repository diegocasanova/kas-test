package com.casanova.kas.test.rest;

import com.casanova.kas.test.rest.model.PackageResult;
import com.casanova.kas.test.web.exception.PackageRestException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@AllArgsConstructor
public class PackageRestClientImpl implements PackageClient {

    private final PackageRestClient packageRestClient;

    @Override
    public PackageResult getPackages(final Integer limit, final Integer offset, final String query) {
        try {
            return packageRestClient.listPackages(query, limit, offset).execute().body();
        } catch (IOException e) {
            throw new PackageRestException(e);
        }
    }

    private String getQueryStr(final String query) {
        if (StringUtils.isEmpty(query)) {
            return null;
        } else {
            return "q=" + query;
        }
    }
}
