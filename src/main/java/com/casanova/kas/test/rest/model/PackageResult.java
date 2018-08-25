package com.casanova.kas.test.rest.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PackageResult {
    private boolean success;
    private int count;
    private List<Package> packages;
}
