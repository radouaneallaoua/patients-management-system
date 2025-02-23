package _gi.projet_dev.patient_management;

import _gi.projet_dev.patient_management.entities.GenderType;
import _gi.projet_dev.patient_management.entities.Patient;
import _gi.projet_dev.patient_management.services.IPatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class PatientManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(PatientManagementApplication.class, args);
	}
//
//	@Bean
//	CommandLineRunner start(IPatientServices patientServices){
//		return args -> {
//
//			Patient p1 = new Patient();
//			p1.setFirstName("Mohamed");
//			p1.setLastName("Amine");
//			p1.setAddress("Casablanca");
//			p1.setGender(GenderType.MALE);
//			patientServices.addPatient(p1);
//
//			Patient p2 = new Patient();
//			p2.setFirstName("Oussama");
//			p2.setLastName("rochdi");
//			p2.setAddress("Stat");
//			p2.setGender(GenderType.MALE);
//			patientServices.addPatient(p2);
//
//			System.out.println(p1.toString());
//		};
//	}

}
