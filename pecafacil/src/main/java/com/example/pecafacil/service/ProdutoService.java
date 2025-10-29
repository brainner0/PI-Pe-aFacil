package com.example.pecafacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pecafacil.model.Produto;
import com.example.pecafacil.repository.ProdutoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setDescricao(produtoAtualizado.getDescricao());
                    produto.setPreco(produtoAtualizado.getPreco());
                    produto.setQuantidade(produtoAtualizado.getQuantidade());
                    produto.setFornecedor(produtoAtualizado.getFornecedor()); // 👈 se tiver o campo fornecedor
                    return produtoRepository.save(produto);
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    // 🔹 Entrada de produtos
    public Produto registrarEntrada(Long id, int quantidade) {
    Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    produto.setQuantidade(produto.getQuantidade() + quantidade);
    produto.setDataUltimaEntrada(LocalDateTime.now());
    return produtoRepository.save(produto);
}

public Produto registrarSaida(Long id, int quantidade) {
    Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    if (produto.getQuantidade() < quantidade) {
        throw new RuntimeException("Estoque insuficiente para saída!");
    }
    produto.setQuantidade(produto.getQuantidade() - quantidade);
    produto.setDataUltimaSaida(LocalDateTime.now());
    return produtoRepository.save(produto);
}
    
}
