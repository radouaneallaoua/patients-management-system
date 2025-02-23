package _gi.projet_dev.patient_management;

import _gi.projet_dev.patient_management.entities.GenderType;
import _gi.projet_dev.patient_management.entities.Patient;
import _gi.projet_dev.patient_management.services.IPatientServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class testinBean {
    private final IPatientServices patientServices;

    public testinBean(IPatientServices patientServices) {
        this.patientServices = patientServices;
    }

    @Bean
    public CommandLineRunner addPatient(){
        return args ->{
            Patient p = new Patient();
            p.setBirthDate(new Date());
            p.setEmail("yassine@mail.com");
            p.setPassword("test");
            p.setFirstName("yassine");
            p.setLastName("bourrich");
            p.setGender(GenderType.MALE);
            p.setAddress("MARRAKECH");
            p.setPhoneNumber("0765379878");

            patientServices.addPatient(p);
            System.out.println(p.getId());
        };
    }
}
