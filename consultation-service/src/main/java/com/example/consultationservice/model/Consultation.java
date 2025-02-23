package com.example.consultationservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timeStamp;
    private String state;
    private String conclusion;

    @Transient
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Diagnostic diagnostics;

    @Column(nullable = false)
    private String patientId;

    @Column(nullable = false)
    private Long doctorId;

    /*@Transient
    private Patient patient;
    @Transient
    private Doctor doctor;*/

}


