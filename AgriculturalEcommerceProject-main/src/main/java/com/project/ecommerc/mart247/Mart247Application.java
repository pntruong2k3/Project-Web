package com.project.ecommerc.mart247;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Mart247Application {

    public static void main(String[] args) {
        SpringApplication.run(Mart247Application.class, args);
    }
}
