package com.casanova.kas.test.rest;

import com.casanova.kas.test.rest.model.PackageResult;

public interface PackageClient {

    PackageResult getPackages(Integer limit, Integer offset, String query);
}
