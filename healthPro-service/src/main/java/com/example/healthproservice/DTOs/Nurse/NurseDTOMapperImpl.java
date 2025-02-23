package com.example.healthproservice.DTOs.Nurse;

import com.example.healthproservice.DTOs.HealthPro.HealthProMapper;
import com.example.healthproservice.model.Nurse;
import org.springframework.stereotype.Service;

@Service
public class NurseDTOMapperImpl implements NurseDTOMapper{
    private final HealthProMapper proMapper;
    public NurseDTOMapperImpl(HealthProMapper proMapper) {
        this.proMapper = proMapper;
    }
    public NurseDTO nurseToDTO(Nurse nurse) {
        NurseDTO dto = new NurseDTO();
        // healthPro general
        dto =  (NurseDTO) proMapper.objectToDTO(nurse,dto);
        // Nurse specific

        return dto;
    }
    public Nurse DTOToNurse(NurseDTO dto) {
        Nurse nurse = new Nurse();
        // healthPro general
        nurse = (Nurse) proMapper.DTOToObject(dto, nurse);
        // Nurse specific

        return nurse;
    }
}
