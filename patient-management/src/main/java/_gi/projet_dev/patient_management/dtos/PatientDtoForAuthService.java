package _gi.projet_dev.patient_management.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
public class PatientDtoForAuthService {
    private String email;
    private String password;
}
