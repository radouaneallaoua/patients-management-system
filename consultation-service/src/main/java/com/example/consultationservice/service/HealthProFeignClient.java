package com.example.consultationservice.service;

import com.example.consultationservice.model.Doctor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "healthPro-service", url = "http://localhost:8085")
public interface HealthProFeignClient {
    @GetMapping("/doctor/{id}")
    Doctor findDoctorById(@PathVariable Long id);

}
