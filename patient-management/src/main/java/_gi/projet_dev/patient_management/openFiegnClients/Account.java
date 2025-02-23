package _gi.projet_dev.patient_management.openFiegnClients;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Account {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private String roleNameLowercase;


}
