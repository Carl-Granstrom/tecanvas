package com.ltu.ladok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LadokApplication {

    public static void main(String[] args) {
        SpringApplication.run(LadokApplication.class, args);
    }

}
