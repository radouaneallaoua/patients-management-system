package com.example.consultationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Doctor {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}
