package com.example.consultationservice.service;

import com.example.consultationservice.model.Consultation;

import java.lang.module.Configuration;
import java.util.List;
import java.util.Optional;

public interface ConsultationService {
    public List<Consultation> getAll();
    public Consultation create(Consultation consultation);
    public Optional<Consultation> getById(Long id);
}
