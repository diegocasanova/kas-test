package com.casanova.kas.test.rest.mapper;

import com.casanova.kas.test.rest.model.Package;
import com.casanova.kas.test.rest.model.PackageResult;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import lombok.val;

import java.lang.reflect.Type;
import java.util.Arrays;

public class PackageResultDeserializer implements JsonDeserializer<PackageResult> {

    @Override
    public PackageResult deserialize(final JsonElement jsonElement, final Type type,
                                     final JsonDeserializationContext context) throws JsonParseException {

        val jsonObject = jsonElement.getAsJsonObject();
        val resultJson = jsonObject.get("result").getAsJsonObject();
        val success = jsonObject.get("success").getAsBoolean();
        val count = resultJson.get("count").getAsInt();
        Package[] packages = context.deserialize(resultJson.get("results"), Package[].class);

        return PackageResult.builder()
            .count(count)
            .success(success)
            .packages(Arrays.asList(packages))
            .build();
    }
}
