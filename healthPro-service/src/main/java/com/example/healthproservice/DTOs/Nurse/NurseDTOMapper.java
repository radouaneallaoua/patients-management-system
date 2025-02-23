package com.example.healthproservice.DTOs.Nurse;


import com.example.healthproservice.model.Nurse;

public interface NurseDTOMapper {
    NurseDTO nurseToDTO(Nurse nurse);
    Nurse DTOToNurse(NurseDTO dto);
}
