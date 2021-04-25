package com.liviaaurich.carteiraservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties({LiquibaseProperties.class})
@EnableFeignClients
public class CarteiraUsuarioApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarteiraUsuarioApiApplication.class, args);
    }

}
