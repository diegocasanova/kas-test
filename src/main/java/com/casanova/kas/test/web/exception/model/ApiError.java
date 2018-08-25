package com.casanova.kas.test.web.exception.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiError {
    private String code;
    private String message;
}
