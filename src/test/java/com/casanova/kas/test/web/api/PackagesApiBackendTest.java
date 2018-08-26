package com.casanova.kas.test.web.api;

import com.casanova.kas.test.service.PackageService;
import com.casanova.kas.test.web.mapper.PackageApiMapper;
import lombok.val;
import org.junit.Before;
import org.junit.Test;

import static com.casanova.kas.test.support.TestFixture.sampleApiPackageList;
import static com.casanova.kas.test.support.TestFixture.samplePkgsDTO;
import static org.mockito.Mockito.*;

public class PackagesApiBackendTest {

    private static final String DEFAULT_LANG = "ca";
    private static final String ENG_LANG = "en";
    private PackageApiMapper mapper;
    private PackageService service;
    private PackagesApiBackend apiBackend;

    @Before
    public void setup() {
        mapper = mock(PackageApiMapper.class);
        service = mock(PackageService.class);
        apiBackend = new PackagesApiBackend(service, mapper, DEFAULT_LANG);
    }

    @Test
    public void shouldReturnValidPackages() {
        val packagesDTO = samplePkgsDTO();
        val packagesApi = sampleApiPackageList();
        when(service.getPackages(20, 20, ENG_LANG, "query")).thenReturn(packagesDTO);
        when(mapper.toApiPackages(packagesDTO.getPackages())).thenReturn(packagesApi);
        apiBackend.listPackages(20, 20, "en", "query");
        verify(service, times(1)).getPackages(20, 20, ENG_LANG, "query");
        verify(mapper, times(1)).toApiPackages(packagesDTO.getPackages());

    }




}
