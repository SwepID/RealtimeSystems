package com.project.things;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ThingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThingsApplication.class, args);
    }

}
