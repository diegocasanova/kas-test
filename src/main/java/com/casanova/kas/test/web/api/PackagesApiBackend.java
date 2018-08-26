package com.casanova.kas.test.web.api;

import com.casanova.kas.test.api.PackagesApiDelegate;
import com.casanova.kas.test.api.model.ApiPackage;
import com.casanova.kas.test.service.PackageService;
import com.casanova.kas.test.service.model.PackagesDTO;
import com.casanova.kas.test.web.mapper.PackageApiMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PackagesApiBackend implements PackagesApiDelegate {

    private static final String COUNT_RESULT_HEADER = "X-Total-Count";

    private final PackageService service;
    private final PackageApiMapper mapper;
    @NotNull
    private final String defaultLang;

    @Override
    public ResponseEntity<List<ApiPackage>> listPackages(final Integer  limit,
                                                  final Integer  offset,
                                                  final String  lang,
                                                  final String  query) {

        val language = Optional.ofNullable(lang).orElse(defaultLang);
        val obtainedPackages = service.getPackages(limit, offset, language, query);
        val packages = mapper.toApiPackages(obtainedPackages.getPackages());
        val headers = getHeaders(obtainedPackages);

        return new ResponseEntity<>(packages, headers, HttpStatus.OK);
    }


    private HttpHeaders getHeaders(final PackagesDTO packages) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(COUNT_RESULT_HEADER, String.valueOf(packages.getCount()));

        return headers;
    }

}
