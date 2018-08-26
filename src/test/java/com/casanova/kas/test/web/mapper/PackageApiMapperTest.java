package com.casanova.kas.test.web.mapper;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;

import static com.casanova.kas.test.support.TestFixture.samplePkgsDTO;
import static org.assertj.core.api.Assertions.assertThat;

public class PackageApiMapperTest {

    private PackageApiMapper mapper;

    @Before
    public void setup() {
        mapper = Mappers.getMapper(PackageApiMapper.class);
    }


    @Test
    public void shouldMapAPackagesDTO() {
        val packagesDto = samplePkgsDTO();
        val mapped = mapper.toApiPackages(packagesDto.getPackages());
        assertThat(mapped).isNotNull();
        assertThat(mapped).hasSize(2);
        val first = mapped.get(0);
        assertThat(first.getCode()).isEqualTo("code1");
        assertThat(first.getOrganizationDescription()).isEqualTo("description");
        assertThat(mapped).extracting("url").contains("http://eng", "http://eng");
    }

    @Test
    public void shouldMapAndEmptyList() {
        val mapped = mapper.toApiPackages(Collections.emptyList());
        assertThat(mapped).isNotNull();
        assertThat(mapped).isEmpty();
    }
}
