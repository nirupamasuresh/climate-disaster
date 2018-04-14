package com.checkr.climate.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.checkr.climate"})
@EnableJpaRepositories(basePackages= "com.checkr.climate")
@EntityScan("com.checkr.climate.entities")
public class ClimateApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClimateApplication.class, args);
    }
}
