package com.casanova.kas.test.rest;

import com.casanova.kas.test.rest.model.PackageResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PackageRestClient {

    @GET("/data/api/3/action/package_search ")
    Call<PackageResult> listPackages(@Query("q") String query, @Query("rows") int limit,
                                     @Query("start") int offset);
}
