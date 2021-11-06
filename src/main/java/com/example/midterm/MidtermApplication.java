package com.example.midterm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MidtermApplication {
    public static void main(String[] args) {
        SpringApplication.run(MidtermApplication.class, args);
    }
}
