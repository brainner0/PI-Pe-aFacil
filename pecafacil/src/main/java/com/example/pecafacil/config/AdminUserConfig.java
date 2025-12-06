package com.example.pecafacil.config;

import com.example.pecafacil.user.Address;
import com.example.pecafacil.user.Role;
import com.example.pecafacil.user.User;
import com.example.pecafacil.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminUserConfig {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createDefaultAdmin() {
        return args -> {

            // verifica se já existe o usuário admin
            if (userRepository.existsByUsername("admin")) {
                return; // já existe, não faz nada
            }

            // se você quiser um endereço vazio só pra preencher a FK
            Address address = Address.builder()
                    .cep("00000-000")
                    .street("Endereço padrão")
                    .number("0")
                    .district("Centro")
                    .city("Goiânia")
                    .state("GO")
                    .build();

            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("123456")) // senha codificada
                    .role(Role.ROLE_ADMIN)
                    .fullName("Administrador do Sistema")
                    .cpf("00000000000")          // coloque um CPF válido se quiser
                    .phone("(62) 0000-0000")
                    .email("admin@pecafacil.local")
                    .address(address)
                    .build();

            userRepository.save(admin);

            System.out.println("🚀 Usuário ADMIN padrão criado: login=admin, senha=123456");
        };
    }
}
