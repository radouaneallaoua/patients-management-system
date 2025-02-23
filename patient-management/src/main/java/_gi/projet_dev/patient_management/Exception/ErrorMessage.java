package _gi.projet_dev.patient_management.Exception;

import lombok.*;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@Setter
@Getter
@Builder
public class ErrorMessage {
    private String message;
    private int code;
    private Date timeStamp;

}
