package com.allaoua.authservice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = AccountNotFoundException.class)
    public ErrorMessage accountNotFound(AccountNotFoundException e) {
        return new ErrorMessage(e.getMessage(),404,new Date());
    }

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ErrorMessage emailAlreadyExists(EmailAlreadyExistsException e) {
        return new ErrorMessage(e.getMessage(), 409,new Date());
    }
}
