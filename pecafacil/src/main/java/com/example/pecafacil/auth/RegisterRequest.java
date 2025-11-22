package com.example.pecafacil.auth;

import lombok.Data;

@Data
public class RegisterRequest {

    private String fullName;
    private String cpf;
    private String phone;
    private String email;

    private String cep;
    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String state;

    private String username;
    private String password;

    private String role;
}
