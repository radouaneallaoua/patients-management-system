package com.example.healthproservice.controller;

import com.example.healthproservice.service.speciality.DoctorSpecialityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/speciality")
public class SpecialityController {
    private final DoctorSpecialityService specialityService;

    public SpecialityController(DoctorSpecialityService specialityRepo) {
        this.specialityService = specialityRepo;
    }

    @GetMapping
    public ResponseEntity<?> getAllSpecialities(){
        return ResponseEntity.ok(specialityService.getAll());
    }

}
