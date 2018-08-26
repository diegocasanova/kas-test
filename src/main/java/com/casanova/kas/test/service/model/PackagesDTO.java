package com.casanova.kas.test.service.model;

import lombok.Data;

import java.util.List;

@Data
public class PackagesDTO {
    private int count;
    private List<PackageDTO> packages;
}
