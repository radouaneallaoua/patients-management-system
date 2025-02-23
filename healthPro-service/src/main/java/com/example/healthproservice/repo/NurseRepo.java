package com.example.healthproservice.repo;

import com.example.healthproservice.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NurseRepo extends JpaRepository<Nurse,Long> {
    Optional<Nurse> findById(Long id);
}
