package com.example.pecafacil.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;

    @Column(nullable = false)
    private int estoqueMinimo;

    private String marca;
    private String local;

    @Column(name = "data_ultima_entrada")
    private LocalDateTime dataUltimaEntrada;

    @Column(name = "data_ultima_saida")
    private LocalDateTime dataUltimaSaida;

    @Column(nullable = false)
    private boolean ativo;
}
