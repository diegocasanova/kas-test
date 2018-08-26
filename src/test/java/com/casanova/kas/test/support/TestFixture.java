package com.casanova.kas.test.support;

import com.casanova.kas.test.api.model.ApiPackage;
import com.casanova.kas.test.rest.model.Package;
import com.casanova.kas.test.rest.model.PackageResult;
import com.casanova.kas.test.service.model.PackageDTO;
import com.casanova.kas.test.service.model.PackagesDTO;
import lombok.val;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TestFixture {

    public static Package samplePkg(final String code) {
        return samplePkg(code, "description");
    }

    public static Package samplePkg(final String code, final String description) {
        val urlMap = new HashMap<String, String>();
        urlMap.put("ca", "http://cat");
        urlMap.put("en", "http://eng");
        urlMap.put("es", "http://spa");
        return Package.builder()
            .code(code)
            .organizationDescription(description)
            .urls(urlMap)
            .build();
    }

    public static PackageResult samplePkgResult() {
        return PackageResult.builder()
            .count(2)
            .success(true)
            .packages(Arrays.asList(samplePkg("code1"), samplePkg("code2")))
            .build();
    }

    public static PackagesDTO samplePkgsDTO() {
        return PackagesDTO.builder()
            .count(2)
            .packages(Arrays.asList(samplePkgDTO("code1"), samplePkgDTO("code2")))
            .build();
    }

    public static PackageDTO samplePkgDTO(final String code) {
        return samplePkgDto(code, "description");
    }

    public static PackageDTO samplePkgDto(final String code, final String description) {
        return PackageDTO.builder()
            .code(code)
            .organizationDescription(description)
            .url("http://eng")
            .build();
    }

    public static ApiPackage sampleApiPackage(final String code, final String description, final String url) {
        val apiPackage = new ApiPackage();
        apiPackage.setCode(code);
        apiPackage.setOrganizationDescription(description);
        apiPackage.setUrl(url);
        return apiPackage;
    }

    public static List<ApiPackage> sampleApiPackageList() {
        return Arrays.asList(sampleApiPackage("code1", "description", "http://eng"),
            sampleApiPackage("code2", "description", "http://eng"));
    }
}
