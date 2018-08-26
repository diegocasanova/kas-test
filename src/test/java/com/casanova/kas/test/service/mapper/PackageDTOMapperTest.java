package com.casanova.kas.test.service.mapper;

import com.casanova.kas.test.rest.model.PackageResult;
import lombok.val;
import org.junit.Test;

import java.util.Arrays;

import static com.casanova.kas.test.support.TestFixture.samplePkg;
import static org.assertj.core.api.Assertions.assertThat;

public class PackageDTOMapperTest {


    @Test
    public void shouldMapAPackageResult() {
        val pkgResult = PackageResult.builder()
            .count(2)
            .success(true)
            .packages(Arrays.asList(samplePkg("code1"), samplePkg("code2")))
            .build();
        val mapped = PackageDTOMapper.INSTANCE.packageResultToDTO(pkgResult, "en");
        assertThat(mapped).isNotNull();
        assertThat(mapped.getPackages()).hasSize(2);
        val first = mapped.getPackages().get(0);
        assertThat(first.getCode()).isEqualTo("code1");
        assertThat(first.getOrganizationDescription()).isEqualTo("description");
        assertThat(mapped.getPackages()).extracting("url").contains("http://eng", "http://eng");
    }


}
