package com.example.consultationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Treatment {
    private String planDetails;
    private LocalDate startsAt;
    private LocalDate endsAt;
    private String state;
    private Diagnostic diagnostic;
}
