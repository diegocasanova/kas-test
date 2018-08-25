package com.casanova.kas.test.rest.model;

import com.casanova.kas.test.service.model.Language;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Package {

    private String code;
    private String urlCa;
    private String urlSp;
    private String urlEn;
    private String organizationDescription;

    public String getUrlByLang(final Language language) {
        switch (language) {
            case CATALAN: return urlCa;
            case ENGLISH: return urlEn;
            case SPANISH: return urlSp;
            default: throw new IllegalArgumentException("Language is not supported.");
        }
    }

}
