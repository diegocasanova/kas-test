package com.casanova.kas.test.rest;

import com.casanova.kas.test.rest.model.PackageResult;
import org.springframework.cache.annotation.Cacheable;

public interface PackageClient {

    @Cacheable("packages")
    PackageResult getPackages(Integer limit, Integer offset, String query);
}
