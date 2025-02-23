package com.example.healthproservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "DoctorSpeciality")
@Data @AllArgsConstructor @NoArgsConstructor
public class DoctorSpeciality {
    @Id
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String Description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality")
    private List<Doctor> doctors;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }
}
