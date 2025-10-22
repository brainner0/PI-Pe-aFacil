package com.example.pecafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pecafacil.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
