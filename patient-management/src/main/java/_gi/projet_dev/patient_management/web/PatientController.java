package _gi.projet_dev.patient_management.web;

import _gi.projet_dev.patient_management.entities.Patient;
import _gi.projet_dev.patient_management.services.IPatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@CrossOrigin("*")
public class PatientController {

    @Autowired
    private IPatientServices patientServices;

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {

        Patient savedPatient = patientServices.addPatient(patient);
        return ResponseEntity.ok(savedPatient);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(patientServices.getAllPatients());
    }



    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable String id) {
        Patient patient = patientServices.getPatient(id);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String id, @RequestBody Patient updatedPatient) {
        Patient existingPatient = patientServices.getPatient(id);
        if (existingPatient != null) {
            updatedPatient.setId(id); // S'assurer que l'ID reste le même
            Patient savedPatient = patientServices.updatePatient(updatedPatient);
            return ResponseEntity.ok(savedPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        Patient existingPatient = patientServices.getPatient(id);
        if (existingPatient != null) {
            patientServices.deletePatient(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
