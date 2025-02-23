package com.allaoua.authservice.dtos;

import jakarta.validation.constraints.NotBlank;



public class AuthenticationRequestDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public @NotBlank String getEmail() {
        return email;
    }

    public @NotBlank String getPassword() {
        return password;
    }


}
