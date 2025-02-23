package com.example.healthproservice.model;

import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
public class Nurse extends HealthPro{
    public Nurse(){super();}
}
