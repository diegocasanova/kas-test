package com.casanova.kas.test.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageDTO {

    private String code;
    private String url;
    private String organizationDescription;

}
