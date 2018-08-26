package com.casanova.kas.test.service.model;

import com.casanova.kas.test.web.exception.LanguageNotSupportedException;

public enum Language {
    CATALAN("ca"),
    ENGLISH("en"),
    SPANISH("es");

    private final String code;

    Language(final String code) {
        this.code = code;
    }

    public static Language fromString(final String code) {
        for (Language lang: Language.values()) {
            if (lang.code.equalsIgnoreCase(code)) {
                return lang;
            }
        }
        throw new LanguageNotSupportedException(code);
    }

}
