package com.example.consultationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Patient {
    private String id;
    private String email;
    private String firstname;
    private String lastname;
}
