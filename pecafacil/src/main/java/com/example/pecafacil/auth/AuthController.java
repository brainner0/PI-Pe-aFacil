package com.example.pecafacil.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.pecafacil.user.UserRepository;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;  // ✅ AGORA ESTÁ INJETADO

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // ---------- NOVAS ROTAS ----------
    
    @GetMapping("/verificar-cpf/{cpf}")
    public ResponseEntity<Boolean> verificarCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(userRepository.existsByCpf(cpf.replaceAll("\\D", "")));
    }

    @GetMapping("/verificar-username/{username}")
    public ResponseEntity<Boolean> verificarUsername(@PathVariable String username) {
        return ResponseEntity.ok(userRepository.existsByUsername(username));
    }
}
