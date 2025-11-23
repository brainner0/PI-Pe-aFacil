package com.example.pecafacil.mov;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoProdutoRepository movimentacaoRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // reforço da regra do SecurityConfig
    public List<MovimentacaoProduto> listarTodas() {
        return movimentacaoRepository.findAll();
    }
}
