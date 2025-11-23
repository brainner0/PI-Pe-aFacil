package com.example.pecafacil.mov;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimentacaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long produtoId;
    private String tipo;      // ENTRADA | SAIDA
    private int quantidade;
    private String usuario;
    private LocalDateTime dataHora;
}
