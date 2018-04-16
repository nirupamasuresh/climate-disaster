package com.fema.disaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Launches the spring boot application
 */
@SpringBootApplication
public class DisasterSummaryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DisasterSummaryApplication.class, args);
    }
}
