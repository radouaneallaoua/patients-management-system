package com.allaoua.authservice.exception;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


public class ErrorMessage {
    private String message;
    private int code;
    private Date timestamp;

    public ErrorMessage(String message, int code, Date timestamp) {
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }


}
