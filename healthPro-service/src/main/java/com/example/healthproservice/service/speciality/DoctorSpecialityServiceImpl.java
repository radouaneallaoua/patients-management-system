package com.example.healthproservice.service.speciality;

import com.example.healthproservice.model.DoctorSpeciality;
import com.example.healthproservice.repo.DoctorSpecialityRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorSpecialityServiceImpl implements DoctorSpecialityService {
    private final DoctorSpecialityRepo specialityRepo;

    public DoctorSpecialityServiceImpl(DoctorSpecialityRepo specialityRepo) {
        this.specialityRepo = specialityRepo;
    }

    @Override
    public List<DoctorSpeciality> getAll() {
        return specialityRepo.findAll();
    }
}
