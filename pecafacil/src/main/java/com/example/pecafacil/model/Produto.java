package com.example.pecafacil.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;

    @Column(nullable = false)
    private int estoqueMinimo = 0;

    private String fornecedor;
    private String local; // Ex: "A32"

    @Column(name = "data_ultima_entrada")
    private LocalDateTime dataUltimaEntrada;

    @Column(name = "data_ultima_saida")
    private LocalDateTime dataUltimaSaida;

    // NOVO → Soft delete
    @Column(nullable = false)
    private boolean ativo = true;

    public Produto() {}

    public Produto(String nome, String descricao, double preco, int quantidade, String fornecedor) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
    }

    // ============================
    // GETTERS & SETTERS
    // ============================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDateTime getDataUltimaEntrada() {
        return dataUltimaEntrada;
    }

    public void setDataUltimaEntrada(LocalDateTime dataUltimaEntrada) {
        this.dataUltimaEntrada = dataUltimaEntrada;
    }

    public LocalDateTime getDataUltimaSaida() {
        return dataUltimaSaida;
    }

    public void setDataUltimaSaida(LocalDateTime dataUltimaSaida) {
        this.dataUltimaSaida = dataUltimaSaida;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
