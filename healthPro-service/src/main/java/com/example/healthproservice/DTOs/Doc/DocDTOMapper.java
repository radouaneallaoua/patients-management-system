package com.example.healthproservice.DTOs.Doc;

import com.example.healthproservice.model.Doctor;

public interface DocDTOMapper {
    Doctor DocDTOToDoc(DoctorDTO doc);
    DoctorDTO  DocToDocTDO(Doctor doc);
}
