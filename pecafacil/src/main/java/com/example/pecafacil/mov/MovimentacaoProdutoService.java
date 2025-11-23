package com.example.pecafacil.mov;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MovimentacaoProdutoService {

    private final MovimentacaoProdutoRepository repo;

    public void registrar(Long produtoId, String tipo, int quantidade, String usuario) {
        MovimentacaoProduto mov = MovimentacaoProduto.builder()
                .produtoId(produtoId)
                .tipo(tipo)
                .quantidade(quantidade)
                .usuario(usuario)
                .dataHora(LocalDateTime.now())
                .build();
        repo.save(mov);
    }
}
