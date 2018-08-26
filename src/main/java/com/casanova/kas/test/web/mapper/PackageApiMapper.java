package com.casanova.kas.test.web.mapper;

import com.casanova.kas.test.api.model.ApiPackage;
import com.casanova.kas.test.service.model.PackageDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper
public interface PackageApiMapper {
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<ApiPackage> toApiPackages(List<PackageDTO> packages);
}
