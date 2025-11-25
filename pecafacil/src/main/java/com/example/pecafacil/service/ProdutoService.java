package com.example.pecafacil.service;

import com.example.pecafacil.audit.AuditService;
import com.example.pecafacil.model.Produto;
import com.example.pecafacil.mov.MovimentacaoProdutoService;
import com.example.pecafacil.repository.ProdutoRepository;
import com.example.pecafacil.security.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repo;
    private final AuditService auditService;
    private final MovimentacaoProdutoService movService;
    private final JwtService jwtService;

    private String usuarioLogado() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public List<Produto> listarTodos() {
        return repo.findByAtivoTrue();
    }

    public Produto buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produto salvar(Produto p) {
        Produto salvo = repo.save(p);
        auditService.registrar(usuarioLogado(), "CRIAR", "Produto", salvo.getId(),
                "Produto criado: " + salvo.getNome());
        return salvo;
    }

    public Produto atualizar(Long id, Produto dados) {
        Produto p = buscarPorId(id);

        p.setNome(dados.getNome());
        p.setDescricao(dados.getDescricao());
        p.setPreco(dados.getPreco());
        p.setQuantidade(dados.getQuantidade());
        p.setMarca(dados.getMarca());
        p.setLocal(dados.getLocal());

        Produto atualizado = repo.save(p);

        auditService.registrar(usuarioLogado(), "ALTERAR", "Produto", atualizado.getId(),
                "Produto atualizado");

        return atualizado;
    }

    public void deletar(Long id) {
        Produto p = buscarPorId(id);
        p.setAtivo(false);
        repo.save(p);

        auditService.registrar(usuarioLogado(), "EXCLUÍDO", "Produto", id,
                "Produto excluído");
    }

    public Produto registrarEntrada(Long id, int qtd) {
        Produto p = buscarPorId(id);
        p.setQuantidade(p.getQuantidade() + qtd);
        p.setDataUltimaEntrada(LocalDateTime.now());

        Produto salvo = repo.save(p);

        movService.registrar(id, "ENTRADA", qtd, usuarioLogado());
        auditService.registrar(usuarioLogado(), "ENTRADA", "Produto", id,
                "Entrada de " + qtd);

        return salvo;
    }

    public Produto registrarSaida(Long id, int qtd) {
        Produto p = buscarPorId(id);

        if (p.getQuantidade() < qtd)
            throw new RuntimeException("Estoque insuficiente!");

        p.setQuantidade(p.getQuantidade() - qtd);
        p.setDataUltimaSaida(LocalDateTime.now());

        Produto salvo = repo.save(p);

        movService.registrar(id, "SAIDA", qtd, usuarioLogado());
        auditService.registrar(usuarioLogado(), "SAIDA", "Produto", id,
                "Saída de " + qtd);

        return salvo;
    }
}
