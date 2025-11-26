package com.example.pecafacil.mov;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.pecafacil.mov.MovimentacaoProdutoDto; // O DTO criado
import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoController {

    // 1. ✅ CORREÇÃO: Injetar o Serviço, que contém a lógica de mapeamento.
    private final MovimentacaoProdutoService movService; 

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    // 2. ✅ CORREÇÃO: Mudar o tipo de retorno para a lista de DTOs.
    public List<MovimentacaoProdutoDto> listarTodas() { 
        
        // 3. ✅ CORREÇÃO: Chamar o método do Serviço que retorna o nome do produto.
        return movService.listarTodasMovimentacoes(); 
    }
    
    // NOTA: Se você tiver outros métodos como registrar, eles também devem ser adicionados aqui.
}