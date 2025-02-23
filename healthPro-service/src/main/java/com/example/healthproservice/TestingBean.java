package com.example.healthproservice;


import com.example.healthproservice.DTOs.Doc.DoctorDTO;
import com.example.healthproservice.service.doctor.DoctorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestingBean {
    private final DoctorService doctorService;

    public TestingBean(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Bean
    public CommandLineRunner addDoctor(){
        return args ->{
            DoctorDTO doc = new DoctorDTO();
            doc.setPassword("test");
            doc.setPhone("0654553377");
            doc.setEmail("houcine@mail.com");
            doc.setBirthDate(LocalDate.now());
            doc.setJoiningDate(LocalDate.now());
            doc.setFirstname("houcine");
            doc.setLastname("elbaz");
            doc.setAddress("agadir");
            doc.setSpecialityId(1);

            doctorService.add(doc);
        };
    }
}
