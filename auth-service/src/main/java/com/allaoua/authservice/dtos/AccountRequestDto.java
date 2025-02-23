package com.allaoua.authservice.dtos;

import com.allaoua.authservice.entities.Account;
import com.allaoua.authservice.enums.AppRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class AccountRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private String roleNameLowercase;

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public @NotNull String getRole() {
        return roleNameLowercase;
    }

    public void setRole(@NotNull String role) {
        this.roleNameLowercase = role;
    }
}
