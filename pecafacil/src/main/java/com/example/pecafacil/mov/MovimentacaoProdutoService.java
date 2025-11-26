package com.example.pecafacil.mov;

import com.example.pecafacil.model.Produto; // Entidade Produto
import com.example.pecafacil.repository.ProdutoRepository; // Seu Repositório de Produto
import com.example.pecafacil.mov.MovimentacaoProdutoDto; // O DTO que você criou
import com.example.pecafacil.mov.MovimentacaoProduto; // Sua Entidade MovimentacaoProduto

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List; // Importante para List
import java.util.stream.Collectors; // Necessário para .collect()

@Service
@RequiredArgsConstructor
public class MovimentacaoProdutoService {

    private final MovimentacaoProdutoRepository repo;
    private final ProdutoRepository produtoRepository;

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
    // ✅ Adicione este método para listar e mapear
    public List<MovimentacaoProdutoDto> listarTodasMovimentacoes() {
        List<MovimentacaoProduto> movimentacoes = repo.findAll(); 

        return movimentacoes.stream()
                .map(m -> {
                    // Lógica para buscar o nome com tratamento de nulidade
                    String nomeProduto;
                    Long produtoId = m.getProdutoId();

                    if (produtoId != null) {
                        nomeProduto = produtoRepository.findById(produtoId)
                                .map(Produto::getNome)
                                .orElse("Produto Não Encontrado (ID: " + produtoId + ")");
                    } else {
                        nomeProduto = "ID do Produto Ausente";
                    }
                    
                    // Mapeia para o DTO
                    return MovimentacaoProdutoDto.builder()
                            .id(m.getId())
                            .produtoNome(nomeProduto) // ✅ Preenchido aqui
                            .tipo(m.getTipo())
                            .quantidade(m.getQuantidade())
                            .dataHora(m.getDataHora())
                            .usuario(m.getUsuario())
                            .build();
                })
                .collect(Collectors.toList());
    }
}

