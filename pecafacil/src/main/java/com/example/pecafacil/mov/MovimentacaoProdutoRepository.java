package com.example.pecafacil.mov;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MovimentacaoProdutoRepository extends JpaRepository<MovimentacaoProduto, Long> {
    List<MovimentacaoProduto> findByProdutoIdOrderByDataHoraDesc(Long id);
}
