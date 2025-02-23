package com.example.healthproservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @PrimaryKeyJoinColumn(name = "HealthProKey")
@Data
public class Admin extends HealthPro {

}
