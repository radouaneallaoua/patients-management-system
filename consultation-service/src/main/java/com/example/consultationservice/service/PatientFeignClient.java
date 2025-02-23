package com.example.consultationservice.service;

import com.example.consultationservice.model.Doctor;
import com.example.consultationservice.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-management", url = "http://localhost:8080")
public interface PatientFeignClient {
    @GetMapping("/patients/{id}")
    Patient findPatientById(@PathVariable String id);
}