package com.gerenciamentoestoque.repository;

import com.gerenciamentoestoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value = "select u from Produto u where u.sku like %?1%")
    Produto buscarPorSku(String nome);

    @Query(value = "select u from Produto u where u.nomeProduto like %:nomeProduto%")
    List<Produto> buscarPorNome(@Param("nomeProduto") String nomeProduto);
}
