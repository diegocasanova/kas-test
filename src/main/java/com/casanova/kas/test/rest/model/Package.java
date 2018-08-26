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
        String url = "";
        switch (language) {
            case CATALAN:
                url = urlCa;
                break;
            case ENGLISH:
                url = urlEn;
                break;
            case SPANISH:
                url = urlSp;
                break;
        }
        return url;
    }

}
