package _gi.projet_dev.patient_management.services;

import _gi.projet_dev.patient_management.Exception.ErrorWhenRegisteringAccountFromPatient;
import _gi.projet_dev.patient_management.entities.Patient;
import _gi.projet_dev.patient_management.openFiegnClients.Account;
import _gi.projet_dev.patient_management.openFiegnClients.AccountClient;
import _gi.projet_dev.patient_management.openFiegnClients.AccountDto;
import _gi.projet_dev.patient_management.repositories.PatientRepository;
//import _gi.projet_dev.patient_management.security.JwtService;
import feign.FeignException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;


@Service
@Transactional
public class PatientServicesImpl implements IPatientServices {

    private PatientRepository patientRepository;
    private final AccountClient accountClient;

    @Override
    public Patient addPatient(Patient patient) {
        Patient savedPatient=null;
        try{
            savedPatient = patientRepository.save(patient);
            Account account=new Account(patient.getEmail(),patient.getPassword(),"patient");
            AccountDto savedAccount=accountClient.registerAccount(account);
            System.out.println(savedAccount);
        } catch (RuntimeException e) {
            throw new ErrorWhenRegisteringAccountFromPatient("error registering account");
        }

        return savedPatient;
    }

    @Override
    public Patient getPatient(String id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        return patient;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        Patient patient1 = patientRepository.save(patient);
        return patient1;
    }

    @Override
    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public PatientServicesImpl(PatientRepository patientRepository, AccountClient accountClient){
        this.patientRepository = patientRepository;
        this.accountClient = accountClient;
    }
}
