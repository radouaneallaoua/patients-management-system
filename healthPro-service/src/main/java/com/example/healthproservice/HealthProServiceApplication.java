package com.example.healthproservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HealthProServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HealthProServiceApplication.class, args);
    }

}
