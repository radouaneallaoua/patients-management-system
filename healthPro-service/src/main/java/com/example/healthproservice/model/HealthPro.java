package com.example.healthproservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class HealthPro {

    public HealthPro(){}

    public HealthPro(Long id, String email, String firstname, String lastname, String address, String phone,
                     @NotNull(message = "set the birthdate.") LocalDate birthDate,
                     @NotNull(message = "set the joining date.") LocalDate joiningDate)
    {
        this.id = id;
        Email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        Address = address;
        this.phone = phone;
        this.birthDate = birthDate;
        this.joiningDate = joiningDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email(message = "Provide a valid email.")
    @Column(unique = true)
    private String Email;

    @NotBlank(message = "set the firstname.")
    private String firstname;
    @NotBlank(message = "set the lastname.")
    private String lastname;
    @NotBlank(message = "set the address.")
    private String Address;
    @NotBlank(message = "set the phone number.")
    private String phone;
    @NotNull(message = "set the birthdate.")
    private LocalDate birthDate;
    @NotNull(message = "set the joining date.")
    private LocalDate joiningDate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }
}
