package com.example.consultationservice;

import com.example.consultationservice.model.Consultation;
import com.example.consultationservice.service.ConsultationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class testingBean {
    private final ConsultationService consultationService;

    public testingBean(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    //@Bean
    public CommandLineRunner createConsultation(){
        return args ->{
            Consultation c = new Consultation();
            c.setConclusion("All will be fine");
            c.setState("pending");
            c.setDoctorId(1L);
            c.setPatientId("42fb2dc1-f44d-4e69-859f-71db6588e060");
            c.setTimeStamp(LocalDateTime.now());

            consultationService.create(c);
        };
    }
}
