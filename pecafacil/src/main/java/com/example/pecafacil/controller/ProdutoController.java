package com.example.pecafacil.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.pecafacil.model.Produto;
import com.example.pecafacil.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listar() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.atualizar(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }
    // Entrada de produtos
    @PatchMapping("/{id}/entrada/{quantidade}")
    public Produto entrada(@PathVariable Long id, @PathVariable int quantidade) {
    return produtoService.registrarEntrada(id, quantidade);
    }

    // Saída de produtos
    @PatchMapping("/{id}/saida/{quantidade}")
    public Produto saida(@PathVariable Long id, @PathVariable int quantidade) {
    return produtoService.registrarSaida(id, quantidade);
    }
}
