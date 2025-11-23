package com.example.pecafacil.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.example.pecafacil.model.Produto;
import com.example.pecafacil.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @GetMapping
    public List<Produto> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        return service.atualizar(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PatchMapping("/{id}/entrada/{qtd}")
    public Produto entrada(@PathVariable Long id, @PathVariable int qtd) {
        return service.registrarEntrada(id, qtd);
    }

    @PatchMapping("/{id}/saida/{qtd}")
    public Produto saida(@PathVariable Long id, @PathVariable int qtd) {
        return service.registrarSaida(id, qtd);
    }
}
