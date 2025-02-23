package com.example.healthproservice.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity @PrimaryKeyJoinColumn(name = "HealthProKey")
@Data
public class Doctor extends HealthPro {
    public Doctor(){
        super ();
    }

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private DoctorSpeciality speciality;

    @Transient
    private Integer SpecialityId;

    public DoctorSpeciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(DoctorSpeciality speciality) {
        this.speciality = speciality;
    }

}
