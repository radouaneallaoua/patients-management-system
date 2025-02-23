package com.example.healthproservice.controller;

import com.example.healthproservice.DTOs.Nurse.NurseDTO;
import com.example.healthproservice.service.nurse.NurseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nurse")
public class NurseController {
    private final NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @GetMapping
    public ResponseEntity<?> fetchAll(){
        return ResponseEntity.ok(nurseService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> createNurse(@Validated @RequestBody NurseDTO nurseDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nurseService.create(nurseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNurse(@PathVariable Long id){
        return  nurseService.getNurseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNurse(@PathVariable Long id,@Validated @RequestBody NurseDTO nurseDTO){
        if (!nurseService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        nurseDTO.setId(id);
        return ResponseEntity.ok(nurseService.updateNurse(nurseDTO)) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNurse(@PathVariable Long id){
        nurseService.deleteNurse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}