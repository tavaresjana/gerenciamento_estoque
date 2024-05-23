package com.gerenciamentoestoque.repository;

import com.gerenciamentoestoque.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    @Query(value = "select u from Proprietario u where u.cnpj like %?1%")
    Proprietario buscarPorCnpj(String cnpj);

    @Query(value= "select u from Proprietario u where u.ativo = true")
    List<Proprietario> buscarProprietariosAtivos();
}
