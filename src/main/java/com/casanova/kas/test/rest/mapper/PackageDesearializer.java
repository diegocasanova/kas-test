package com.casanova.kas.test.rest.mapper;

import com.casanova.kas.test.rest.model.Package;
import com.google.gson.*;
import lombok.val;

import java.lang.reflect.Type;

public class PackageDesearializer implements JsonDeserializer<Package> {

    @Override
    public Package deserialize(final JsonElement jsonElement, final Type type,
                               final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        val jsonObject = jsonElement.getAsJsonObject();
        val code = jsonObject.get("code").getAsString();
        val orgJson = jsonObject.get("organization").getAsJsonObject();
        val orgDescription = orgJson.get("description").getAsString();
        val urlsJson = jsonObject.get("url_tornada").getAsJsonObject();
        val urlCa = urlsJson.get("ca").getAsString();
        val urlEn = urlsJson.get("en").getAsString();
        val urlSp = urlsJson.get("es").getAsString();

        return Package.builder()
            .code(code)
            .organizationDescription(orgDescription)
            .urlCa(urlCa)
            .urlEn(urlEn)
            .urlSp(urlSp)
            .build();
    }
}
