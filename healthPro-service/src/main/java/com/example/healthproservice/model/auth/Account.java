package com.example.healthproservice.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Account {
    private String email;
    private String password;
    private String roleNameLowercase;
    private String role;
}
