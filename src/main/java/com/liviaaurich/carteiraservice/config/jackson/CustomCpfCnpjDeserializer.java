package com.liviaaurich.carteiraservice.config.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Objects;

public class CustomCpfCnpjDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String string = null;
        if (Objects.nonNull(jsonParser.getValueAsString())) {
            string = jsonParser.getValueAsString().replaceAll("[^0-9]", "");
        }
        return string;
    }
}
