package com.allaoua.authservice.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {}
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
