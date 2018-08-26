package com.casanova.kas.test.web;

import com.casanova.kas.test.service.PackageService;
import com.casanova.kas.test.spring.Application;
import com.casanova.kas.test.web.mapper.PackageApiMapper;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.casanova.kas.test.support.TestFixture.sampleApiPackageList;
import static com.casanova.kas.test.support.TestFixture.samplePkgsDTO;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
public class PackageControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PackageService packageService;

    @MockBean
    private PackageApiMapper packageApiMapper;

    @Test
    public void shouldReturnAValidResponseWhenNoParameters() throws Exception {
        val pkgsDTO = samplePkgsDTO();
        val pkgsAPI = sampleApiPackageList();
        when(packageService.getPackages(10, 0, "ca", null)).thenReturn(pkgsDTO);
        when(packageApiMapper.toApiPackages(pkgsDTO.getPackages())).thenReturn(pkgsAPI);
        mockMvc.perform(get("/api/v1/packages").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(header().string("X-Total-Count", "2"))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].code", is("code1")))
            .andExpect(jsonPath("$[0].url", is("http://eng")));
        verify(packageService, times(1)).getPackages(10, 0, "ca", null);
    }


    @Test
    public void shouldReturnAValidResponseWhenQueryParams() throws Exception {
        val pkgsDTO = samplePkgsDTO();
        val pkgsAPI = sampleApiPackageList();
        when(packageService.getPackages(2, 10, "en", "test")).thenReturn(pkgsDTO);
        when(packageApiMapper.toApiPackages(pkgsDTO.getPackages())).thenReturn(pkgsAPI);
        mockMvc.perform(get("/api/v1/packages")
            .param("lang", "en")
            .param("limit", "2")
            .param("offset", "10")
            .param("query", "test")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(header().string("X-Total-Count", "2"))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].code", is("code1")))
            .andExpect(jsonPath("$[0].url", is("http://eng")));
        verify(packageService, times(1)).getPackages(2, 10, "en", "test");
    }

    @Test
    public void shouldReturnAValidResponseEvenWhenRuntimeException() throws Exception {
        val pkgsDTO = samplePkgsDTO();
        val pkgsAPI = sampleApiPackageList();
        when(packageService.getPackages(10, 0, "ca", "theQuery"))
            .thenThrow(new RuntimeException("a runtime exception"));
        when(packageApiMapper.toApiPackages(pkgsDTO.getPackages())).thenReturn(pkgsAPI);
        mockMvc.perform(get("/api/v1/packages")
            .param("query", "theQuery")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.code", is("API-000")))
            .andExpect(jsonPath("$.message", is("There was error processing your request. Please contact the system administrator.")));
        verify(packageService, times(1)).getPackages(10, 0, "ca", "theQuery");
    }
}
