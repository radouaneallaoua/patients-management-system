package _gi.projet_dev.patient_management.entities;

import _gi.projet_dev.patient_management.dtos.PatientDtoForAuthService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Entity
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    //Attention: @ToString.Exclude est utilise pour que la methode toString() n'affiche pas un attribut (raison de sécurité)
    @ToString.Exclude
    private String id;
    private String lastName;
    private String firstName;
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String email;
    @ToString.Exclude
    private String password;
    private String address;
    private String phoneNumber;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    //    public PatientDtoForAuthService toDto() {
//        return  PatientDtoForAuthService.builder()
//                .email(email)
//                .password(password)
//                .build();
//    }

}
