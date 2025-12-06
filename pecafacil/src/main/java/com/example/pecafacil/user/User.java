package com.example.pecafacil.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    // =============================
    // Dados de acesso
    // =============================
    @Column(unique = true, nullable = false)
    private String username;   // login

    @Column(nullable = false)
    private String password;   // senha HASH (BCrypt)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // =============================
    // Dados pessoais
    // =============================
    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false, length = 14)
    private String cpf;     // armazenado já formatado (000.000.000-00)

    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean active = false; // Padrão é falso (bloqueado)

    // =============================
    // Endereço (One To One)
    // =============================
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}
