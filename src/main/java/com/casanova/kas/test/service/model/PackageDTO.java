package com.casanova.kas.test.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PackageDTO {

    private String code;
    private String url;
    private String organizationDescription;

}
