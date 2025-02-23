package com.example.consultationservice.service;

import com.example.consultationservice.model.Consultation;
import com.example.consultationservice.model.Doctor;
import com.example.consultationservice.model.Patient;
import com.example.consultationservice.repo.ConsultationRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationServiceImpl implements ConsultationService{
    final private ConsultationRepo repo;
    final private HealthProFeignClient healthProFeignClient;
    private final PatientFeignClient patientFeignClient;
    public ConsultationServiceImpl(ConsultationRepo repo, HealthProFeignClient healthProFeignClient, PatientFeignClient patientFeignClient) {
        this.repo = repo;
        this.healthProFeignClient = healthProFeignClient;
        this.patientFeignClient = patientFeignClient;
    }

    public Consultation create(Consultation consultation){
        Doctor doc = healthProFeignClient.findDoctorById(consultation.getDoctorId());
        Patient pat = patientFeignClient.findPatientById(consultation.getPatientId());
        return repo.save(consultation);
    }

    public Optional<Consultation> getById(Long id){
        return repo.findById(id);
    }

    public List<Consultation> getAll(){
        return repo.findAll();
    }

}
