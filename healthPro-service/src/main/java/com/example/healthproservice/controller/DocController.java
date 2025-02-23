package com.example.healthproservice.controller;

import com.example.healthproservice.DTOs.Doc.DoctorDTO;
import com.example.healthproservice.service.doctor.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@CrossOrigin("*")
public class DocController {
    private final DoctorService doctorService;

    public DocController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<?> createDoctor(@Validated @RequestBody DoctorDTO docTDO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(doctorService.add(docTDO));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateDoctor(@Validated @RequestBody DoctorDTO docTDO,@PathVariable Long id){
        docTDO.setId(id);
        return ResponseEntity.ok( doctorService.updateDoctor(docTDO));
    }

    @GetMapping
    public List<DoctorDTO> getAll(){
        return doctorService.getAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return  doctorService.getDoctor(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
