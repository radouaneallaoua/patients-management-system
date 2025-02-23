package com.example.healthproservice.service.nurse;

import com.example.healthproservice.DTOs.Nurse.NurseDTO;
import com.example.healthproservice.model.Nurse;

import java.util.List;
import java.util.Optional;

public interface NurseService {
    List<NurseDTO> getAll();

    NurseDTO create(NurseDTO nurseDTO);

    NurseDTO updateNurse(NurseDTO nurseDTO);

    void deleteNurse(Long id);

    Optional<NurseDTO> getNurseById(Long id);

    boolean existById(Long id);
}
