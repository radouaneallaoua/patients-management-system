package _gi.projet_dev.patient_management.repositories;

import _gi.projet_dev.patient_management.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,String> {

}
