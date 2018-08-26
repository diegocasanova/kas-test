package com.casanova.kas.test.rest.mapper;

import com.casanova.kas.test.rest.model.Package;
import com.casanova.kas.test.rest.model.PackageResult;
import com.google.gson.*;
import lombok.val;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class GsonDeserializersTest {

    private static final String JSON_OK_PATH = "wiremock/__files/packages_response_ok.json";
    private static final String JSON_INCORRECT_PATH = "wiremock/__files/packages_response_incorrect.json";
    private Gson gson;

    @Before
    public void setup() throws IOException, URISyntaxException {
        val gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Package.class, new PackageDesearializer());
        gsonBuilder.registerTypeAdapter(PackageResult.class, new PackageResultDeserializer());
        gson = gsonBuilder.create();
    }


    @Test
    public void shouldDeserializeAValidResponse() throws IOException, URISyntaxException {
        val json = readFileAsJsonObject(JSON_OK_PATH);
        val packageResult = gson.fromJson(json, PackageResult.class);
        assertThat(packageResult).isNotNull();
        assertThat(packageResult.isSuccess()).isTrue();
        assertThat(packageResult.getCount()).isEqualTo(2);
        assertThat(packageResult.getPackages()).hasSize(2);
        val first = packageResult.getPackages().get(0);
        assertThat(first.getCode()).isEqualTo("CATALEG_OPENDATA");
        assertThat(first.getOrganizationDescription()).isEqualTo("Public sector");
        assertThat(first.getUrlCa()).isEqualTo("http://opendata-ajuntament.barcelona.cat/");
        assertThat(first.getUrlEn()).isEqualTo("http://opendata-ajuntament.barcelona.cat/en/");
        assertThat(first.getUrlSp()).isEqualTo("http://opendata-ajuntament.barcelona.cat/es/");
    }

    @Test(expected = JsonSyntaxException.class)
    public void shouldThrowAndExceptionIfInvalidJson() throws IOException, URISyntaxException {
        val json = readFileAsJsonObject(JSON_INCORRECT_PATH);
        val packageResult = gson.fromJson(json, PackageResult.class);
    }

    private JsonObject readFileAsJsonObject(final String path) throws URISyntaxException, IOException {
        val bytes = Files.readAllBytes(Paths.get(getClass().getClassLoader()
            .getResource(path).toURI()));
        val jsonData = new String(bytes, StandardCharsets.UTF_8);
        val parser = new JsonParser();
        return parser.parse(jsonData).getAsJsonObject();
    }

}
