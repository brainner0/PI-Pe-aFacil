package com.example.pecafacil.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private String role; // "USER" ou "ADMIN"
}
