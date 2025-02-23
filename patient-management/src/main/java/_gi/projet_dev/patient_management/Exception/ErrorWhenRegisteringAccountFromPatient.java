package _gi.projet_dev.patient_management.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;


public class ErrorWhenRegisteringAccountFromPatient extends RuntimeException {
    public ErrorWhenRegisteringAccountFromPatient(String message) {
        super(message);
    }
    public ErrorWhenRegisteringAccountFromPatient() {}

}
