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

    @Column(unique = true, nullable = false)
    private String username;   // login

    @Column(nullable = false)
    private String password;   // senha HASH (BCrypt)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
