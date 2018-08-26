package com.casanova.kas.test.rest.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
public class Package implements Serializable {

    private String code;
    private Map<String, String> urls;
    private String organizationDescription;

    public String getUrlByLanguage(final String language) {
        return urls.get(language);
    }


}
