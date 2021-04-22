package com.liviaaurich.carteiraservice.config.jackson;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper jsonObjectMapper() {
        ArrayList<Module> modules = new ArrayList<>();

        SimpleModule module = new SimpleModule();
//        module.addDeserializer(String.class, new CustomCpfCnpjDeserializer());
        modules.add(module);

        return Jackson2ObjectMapperBuilder.json().modules(modules).build();
    }

}
