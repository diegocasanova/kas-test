package com.casanova.kas.test.service;

import com.casanova.kas.test.rest.PackageClient;
import com.casanova.kas.test.service.mapper.PackageDTOMapper;
import com.casanova.kas.test.service.model.Language;
import lombok.val;
import org.junit.Before;
import org.junit.Test;

import static com.casanova.kas.test.support.TestFixture.samplePkgResult;
import static com.casanova.kas.test.support.TestFixture.samplePkgsDTO;
import static org.mockito.Mockito.*;

public class PackageServiceTest {

    private static final Integer DEFAULT_OFFSET = 10;
    private static final Integer DEFAULT_LIMIT = 10;

    private PackageDTOMapper mapper;
    private PackageClient packageClient;


    private PackageServiceImpl packageService;

    @Before
    public void setup() {
        mapper = mock(PackageDTOMapper.class);
        packageClient = mock(PackageClient.class);
        packageService = new PackageServiceImpl(mapper, packageClient, DEFAULT_OFFSET, DEFAULT_LIMIT);
    }

    @Test
    public void shouldReturnValidPackagesDto() {
        val pkgResult = samplePkgResult();
        when(packageClient.getPackages(20,30, "query")).thenReturn(pkgResult);
        when(mapper.packageResultToDTO(pkgResult, Language.ENGLISH)).thenReturn(samplePkgsDTO());
        val result = packageService.getPackages(20, 30, Language.ENGLISH, "query");
        verify(mapper, times(1)).packageResultToDTO(pkgResult, Language.ENGLISH);
        verify(packageClient, times(1)).getPackages(20, 30, "query");
    }

    @Test
    public void shouldUseDefaultValuesIfNullParameters() {
        val pkgResult = samplePkgResult();
        when(packageClient.getPackages(DEFAULT_LIMIT, DEFAULT_OFFSET, "query")).thenReturn(pkgResult);
        when(mapper.packageResultToDTO(pkgResult, Language.SPANISH)).thenReturn(samplePkgsDTO());
        val result = packageService.getPackages(null, null, Language.SPANISH, "query");
        verify(mapper, times(1)).packageResultToDTO(pkgResult, Language.SPANISH);
        verify(packageClient, times(1)).getPackages(DEFAULT_LIMIT, DEFAULT_OFFSET, "query");
    }




}
