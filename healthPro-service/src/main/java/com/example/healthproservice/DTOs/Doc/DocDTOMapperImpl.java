package com.example.healthproservice.DTOs.Doc;

import com.example.healthproservice.model.Doctor;
import org.springframework.stereotype.Component;


@Component
public class DocDTOMapperImpl implements DocDTOMapper{

    @Override
    public Doctor DocDTOToDoc(DoctorDTO doc) {
        Doctor doctor = new Doctor();
        // HealthPro general
        doctor.setId(doc.getId());
        doctor.setEmail(doc.getEmail());
        doctor.setFirstname(doc.getFirstname());
        doctor.setLastname(doc.getLastname());
        doctor.setAddress(doc.getAddress());
        doctor.setPhone(doc.getPhone());
        doctor.setBirthDate(doc.getBirthDate());
        doctor.setJoiningDate(doc.getJoiningDate());
        // Doc specific
        doctor.setSpecialityId(doc.getSpecialityId());

        return doctor;
    }
    @Override
    public DoctorDTO DocToDocTDO(Doctor doc){
        DoctorDTO doctor = new DoctorDTO();
        doctor.setFirstname(doc.getFirstname());
        doctor.setAddress(doc.getAddress());
        doctor.setLastname(doc.getLastname());
        doctor.setBirthDate(doc.getBirthDate());
        doctor.setPhone(doc.getPhone());
        doctor.setEmail(doc.getEmail());

        doctor.setJoiningDate(doc.getJoiningDate());
        doctor.setSpeciality(doc.getSpeciality().getName());
        doctor.setSpecialityId(doc.getSpeciality().getId());
        doctor.setId(doc.getId());
        return doctor;
    }
}
