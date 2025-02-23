package com.example.healthproservice.repo;

import com.example.healthproservice.model.DoctorSpeciality;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DoctorSpecialityRepo extends JpaRepository<DoctorSpeciality, Integer> {
    List<DoctorSpeciality> findAll();
    Optional<DoctorSpeciality> findById(Integer id);
}
