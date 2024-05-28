package com.gerenciamentoestoque.repository;

import com.gerenciamentoestoque.model.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
    List<Unidade> findByProprietarioId(Long proprietarioId);
}
