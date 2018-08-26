package com.casanova.kas.test.rest.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class PackageResult implements Serializable {
    private boolean success;
    private int count;
    private List<Package> packages;
}
