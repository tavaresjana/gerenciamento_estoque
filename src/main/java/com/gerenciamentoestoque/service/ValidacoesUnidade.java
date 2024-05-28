package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.dto.UnidadeDto;
import com.gerenciamentoestoque.handler.exceptions.NomeInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.UnidadeNaoEncontradaException;
import com.gerenciamentoestoque.model.Unidade;
import com.gerenciamentoestoque.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidacoesUnidade {

    @Autowired
    private UnidadeRepository unidadeRepository;

    public void verificarId(Long id) {
        Optional<Unidade> unidadeOptional = unidadeRepository.findById(id);
        if (unidadeOptional == null || unidadeOptional.isEmpty()) {
            throw new UnidadeNaoEncontradaException();
        }
    }

    public void verificarNome(UnidadeDto unidadeDto) {
        if (unidadeDto.getNomeUnidade().length() < 2 || unidadeDto.getNomeUnidade().length() > 15) {
            throw new NomeInvalidoException("Nome inválido. O nome da unidade deverá ter entre 2 e 15 caracteres.");
        }
    }
}
