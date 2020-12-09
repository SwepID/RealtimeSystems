package com.project.things;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableScheduling
public class ThingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThingsApplication.class, args);
    }
    @Scheduled(fixedRate = 60000)
    public void perform(){
        System.out.println("Прошло 60 сек!");
    }
}
