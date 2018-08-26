package com.casanova.kas.test.service;

import com.casanova.kas.test.rest.PackageClient;
import com.casanova.kas.test.rest.model.PackageResult;
import com.casanova.kas.test.service.mapper.PackageDTOMapper;
import com.casanova.kas.test.service.model.PackagesDTO;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.Optional;

@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final PackageDTOMapper mapper;
    private final PackageClient packageClient;
    private final Integer defaultOffset;
    private final Integer defaultLimit;


    @Override
    public PackagesDTO getPackages(final Integer limit, final Integer offset, final String lang,
                                             final String query) {
        val start = Optional.ofNullable(offset).orElse(defaultOffset);
        val max = Optional.ofNullable(limit).orElse(defaultLimit);

        val result = invokeClient(max, start, query);

        return mapper.packageResultToDTO(result, lang);
    }

    private PackageResult invokeClient(final Integer limit, final Integer offset, final String query) {
            return packageClient.getPackages(limit, offset, query);

    }

}
