package com.casanova.kas.test.service;

import com.casanova.kas.test.service.model.PackagesDTO;

public interface PackageService {

    PackagesDTO getPackages(Integer limit, Integer offset, String lang, String query);
}
