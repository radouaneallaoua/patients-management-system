package com.example.healthproservice.service.doctor;

import com.example.healthproservice.DTOs.Doc.DoctorDTO;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DoctorService {
    DoctorDTO add(DoctorDTO doc);
    List<DoctorDTO> getAll();
    Optional<DoctorDTO> getDoctor(Long id);
    void deleteDoctor(Long id);

    DoctorDTO updateDoctor(DoctorDTO doctorDTO);

}
