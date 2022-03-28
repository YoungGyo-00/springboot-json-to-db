package com.example.jsontodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JsontodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsontodbApplication.class, args);
    }

}
