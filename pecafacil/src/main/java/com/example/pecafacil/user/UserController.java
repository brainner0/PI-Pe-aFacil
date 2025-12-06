package com.example.pecafacil.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // Listar apenas usuários pendentes (Inativos)
    @GetMapping("/pending")
    public ResponseEntity<List<User>> listarPendentes() {
        return ResponseEntity.ok(userRepository.findByActiveFalse());
    }

    // Aprovar usuário (Mudar active para true)
    @PatchMapping("/{id}/approve")
    public ResponseEntity<Void> aprovarUsuario(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        user.setActive(true);
        userRepository.save(user);
        
        return ResponseEntity.noContent().build();
    }
    
    // Recusar/Excluir usuário (Opcional, se quiser um botão de rejeitar)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> recusarUsuario(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<User>> listarAtivos() {
        return ResponseEntity.ok(userRepository.findByActiveTrue());
    }
}
