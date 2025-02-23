package com.example.healthproservice.DTOs.HealthPro;

import com.example.healthproservice.model.HealthPro;
import com.example.healthproservice.model.Nurse;
import org.springframework.stereotype.Service;

@Service
public class HealthProMapperImpl implements HealthProMapper {
    @Override
    public HealthProDTO objectToDTO(HealthPro pro, HealthProDTO proDTO) {
        proDTO.setEmail(pro.getEmail());
        proDTO.setId(pro.getId());
        proDTO.setLastname(pro.getLastname());
        proDTO.setFirstname(pro.getFirstname());
        proDTO.setAddress(pro.getAddress());
        proDTO.setPhone(pro.getPhone());
        proDTO.setBirthDate(pro.getBirthDate());
        proDTO.setJoiningDate(pro.getJoiningDate());
        return proDTO;
    }
    @Override
    public HealthPro DTOToObject(HealthProDTO proDTO, HealthPro pro) {
        pro.setEmail(proDTO.getEmail());
        pro.setFirstname(proDTO.getFirstname());
        pro.setId(proDTO.getId());
        pro.setLastname(proDTO.getLastname());
        pro.setAddress(proDTO.getAddress());
        pro.setPhone(proDTO.getPhone());
        pro.setBirthDate(proDTO.getBirthDate());
        pro.setJoiningDate(proDTO.getJoiningDate());
        return pro;
    }
}
