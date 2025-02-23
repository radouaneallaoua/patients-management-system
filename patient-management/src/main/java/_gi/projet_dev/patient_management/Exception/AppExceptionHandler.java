package _gi.projet_dev.patient_management.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(ErrorWhenRegisteringAccountFromPatient.class)
    public ResponseEntity<ErrorMessage> error(ErrorWhenRegisteringAccountFromPatient e){
        ErrorMessage errorMessage=new ErrorMessage(e.getMessage(),401,new Date());
        return ResponseEntity.ok(errorMessage);
    }
}
