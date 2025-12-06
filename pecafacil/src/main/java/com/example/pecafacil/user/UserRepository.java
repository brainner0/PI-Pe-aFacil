package com.example.pecafacil.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByCpf(String cpf);

    List<User> findByActiveFalse(); // Busca todos os usuários inativos (pendentes)
    List<User> findByActiveTrue();
}
