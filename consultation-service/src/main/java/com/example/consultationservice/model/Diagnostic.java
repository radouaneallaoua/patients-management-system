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
public class Diagnostic {
    private Long consultationId;
    private String symptoms;
    private String condition;
    private LocalDate date;
    private String notes;
    private Treatment treatment;
}
