package _gi.projet_dev.patient_management.services;

import _gi.projet_dev.patient_management.dtos.PatientDtoForAuthService;
import _gi.projet_dev.patient_management.entities.Patient;
import feign.FeignException;

import java.util.List;

public interface IPatientServices {
     Patient addPatient(Patient patient) throws FeignException;
     Patient getPatient(String id);
     Patient updatePatient(Patient patient);
     void deletePatient(String id);
     List<Patient> getAllPatients();
    //List<PatientDtoForAuthService> getAllPatientsDtoForAuthService();

}
