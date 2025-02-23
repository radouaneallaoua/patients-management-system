package com.example.healthproservice.DTOs.Doc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor
@Data
public class DoctorDTO {

    private Long id;
    @Email
    private String email;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
    @NotNull @DateTimeFormat
    private LocalDate birthDate;
    @NotNull @DateTimeFormat
    private LocalDate joiningDate;

    @JsonProperty(required = true)
    private String speciality;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int specialityId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}

