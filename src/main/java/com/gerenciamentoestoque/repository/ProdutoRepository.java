package com.gerenciamentoestoque.repository;

import com.gerenciamentoestoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
