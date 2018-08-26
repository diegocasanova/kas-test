package com.casanova.kas.test.service.mapper;

import com.casanova.kas.test.rest.model.Package;
import com.casanova.kas.test.rest.model.PackageResult;
import com.casanova.kas.test.service.model.PackageDTO;
import com.casanova.kas.test.service.model.PackagesDTO;
import org.mapstruct.Context;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class PackageDTOMapper {

    public static PackageDTOMapper INSTANCE = Mappers.getMapper( PackageDTOMapper.class );

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    public abstract List<PackageDTO> toPackagesDTO(List<Package> packages, @Context String language);

    public abstract PackagesDTO packageResultToDTO(PackageResult result, @Context String language);


    public PackageDTO packageToPackageDto(final Package pkg, @Context final String language) {
        return PackageDTO.builder()
            .code(pkg.getCode())
            .organizationDescription(pkg.getOrganizationDescription())
            .url(pkg.getUrlByLanguage(language))
            .build();
    }
}
