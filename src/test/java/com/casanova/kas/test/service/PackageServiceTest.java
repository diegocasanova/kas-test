package com.casanova.kas.test.service;

import com.casanova.kas.test.rest.PackageClient;
import com.casanova.kas.test.service.mapper.PackageDTOMapper;
import lombok.val;
import org.junit.Before;
import org.junit.Test;

import static com.casanova.kas.test.support.TestFixture.samplePkgResult;
import static com.casanova.kas.test.support.TestFixture.samplePkgsDTO;
import static org.mockito.Mockito.*;

public class PackageServiceTest {

    private static final Integer DEFAULT_OFFSET = 10;
    private static final Integer DEFAULT_LIMIT = 10;
    private static final String LANGUAGE_EN = "en";
    private static final String LANGUAGE_SP = "es";

    private PackageDTOMapper mapper;
    private PackageClient packageClient;


    private PackageServiceImpl packageService;

    @Before
    public void setup() {
        mapper = mock(PackageDTOMapper.class);
        packageClient = mock(PackageClient.class);
        packageService = new PackageServiceImpl(mapper, packageClient, DEFAULT_LIMIT);
    }

    @Test
    public void shouldReturnValidPackagesDto() {
        val pkgResult = samplePkgResult();
        when(packageClient.getPackages(20,30, "query")).thenReturn(pkgResult);
        when(mapper.packageResultToDTO(pkgResult, LANGUAGE_EN)).thenReturn(samplePkgsDTO());
        val result = packageService.getPackages(20, 30, LANGUAGE_EN, "query");
        verify(mapper, times(1)).packageResultToDTO(pkgResult, LANGUAGE_EN);
        verify(packageClient, times(1)).getPackages(20, 30, "query");
    }

    @Test
    public void shouldUseDefaultValuesIfNullParameters() {
        val pkgResult = samplePkgResult();
        when(packageClient.getPackages(DEFAULT_LIMIT, null, "query")).thenReturn(pkgResult);
        when(mapper.packageResultToDTO(pkgResult, LANGUAGE_SP)).thenReturn(samplePkgsDTO());
        val result = packageService.getPackages(null, null, LANGUAGE_SP, "query");
        verify(mapper, times(1)).packageResultToDTO(pkgResult, LANGUAGE_SP);
        verify(packageClient, times(1)).getPackages(DEFAULT_LIMIT, null, "query");
    }




}
