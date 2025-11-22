package com.example.pecafacil.auth;

import com.example.pecafacil.security.JwtService;
import com.example.pecafacil.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Usuário já existe");
        }

        // CPF validator simples
        if (!CpfValidator.isValid(request.getCpf())) {
            throw new RuntimeException("CPF inválido");
        }

        Address address = Address.builder()
                .cep(request.getCep())
                .street(request.getStreet())
                .number(request.getNumber())
                .complement(request.getComplement())
                .district(request.getDistrict())
                .city(request.getCity())
                .state(request.getState())
                .build();

        Role role = request.getRole().equalsIgnoreCase("ROLE_ADMIN")
                ? Role.ROLE_ADMIN
                : Role.ROLE_USER;

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .fullName(request.getFullName())
                .cpf(request.getCpf())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(address)
                .build();

        userRepository.save(user);

        UserDetails userDetails =
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .authorities(user.getRole().name())
                        .build();

        return new AuthResponse(
                jwtService.generateToken(userDetails, user.getRole().name()),
                user.getUsername(),
                user.getRole().name()
        );
    }

    public AuthResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new AuthResponse(
                jwtService.generateToken(
                        (UserDetails) auth.getPrincipal(),
                        user.getRole().name()
                ),
                user.getUsername(),
                user.getRole().name()
        );
    }
}
