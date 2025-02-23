package com.example.consultationservice.repo;

import com.example.consultationservice.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ConsultationRepo extends JpaRepository<Consultation,Long> {
    List<Consultation> findAll();
    Optional<Consultation> findById(Long id);
}
