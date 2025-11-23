package com.example.pecafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pecafacil.model.Produto;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByAtivoTrue();
}
