package com.example.healthproservice.DTOs.HealthPro;

import com.example.healthproservice.model.HealthPro;
import com.example.healthproservice.model.Nurse;

public interface HealthProMapper {
    HealthProDTO objectToDTO(HealthPro pro,HealthProDTO proDTO);
    HealthPro DTOToObject(HealthProDTO proDTO, HealthPro nurse);
}
