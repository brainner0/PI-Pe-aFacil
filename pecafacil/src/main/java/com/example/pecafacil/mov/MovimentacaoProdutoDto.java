package com.example.pecafacil.mov;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data 
@Builder 
public class MovimentacaoProdutoDto {
    private Long id;
    private String produtoNome; // ✅ Campo novo que o Frontend espera
    private String tipo;
    private int quantidade;
    private LocalDateTime dataHora;
    private String usuario;
}