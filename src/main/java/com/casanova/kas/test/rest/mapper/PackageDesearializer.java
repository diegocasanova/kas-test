package com.casanova.kas.test.rest.mapper;

import com.casanova.kas.test.rest.model.Package;
import com.google.gson.*;
import lombok.val;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PackageDesearializer implements JsonDeserializer<Package> {

    @Override
    public Package deserialize(final JsonElement jsonElement, final Type type,
                               final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        val jsonObject = jsonElement.getAsJsonObject();
        val code = jsonObject.get("code").getAsString();
        val orgJson = jsonObject.get("organization").getAsJsonObject();
        val orgDescription = orgJson.get("description").getAsString();
        val urlsJson = jsonObject.get("url_tornada").getAsJsonObject();

        val urlMap = new HashMap<String, String>();
        Set<Map.Entry<String, JsonElement>> entrySet = urlsJson.entrySet();
        for (Map.Entry<String, JsonElement> entry : entrySet){
            urlMap.put(entry.getKey(), urlsJson.get(entry.getKey()).getAsString());
        }

        return Package.builder()
            .code(code)
            .organizationDescription(orgDescription)
            .urls(urlMap)
            .build();
    }
}
