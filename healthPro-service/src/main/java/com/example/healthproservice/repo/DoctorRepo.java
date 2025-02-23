package com.example.healthproservice.repo;

import com.example.healthproservice.DTOs.Doc.DoctorDTO;
import com.example.healthproservice.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public interface DoctorRepo extends JpaRepository<Doctor,Long> {
    List<Doctor> findAll() ;
    Optional<Doctor> findById(Long id);
}
