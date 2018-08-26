package com.casanova.kas.test.service;

import com.casanova.kas.test.service.model.Language;
import com.casanova.kas.test.service.model.PackagesDTO;

import java.util.Optional;

public interface PackageService {
    PackagesDTO getPackages(Integer limit, Integer offset, Language lang, String query);
}
