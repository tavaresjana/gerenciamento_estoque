package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProprietarioDto;
import com.gerenciamentoestoque.handler.exceptions.CnpjInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.ProprietarioNaoEncontradoException;
import com.gerenciamentoestoque.model.Proprietario;
import com.gerenciamentoestoque.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidacoesProprietario {

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    public void verificarId(Long id) {
        Optional<Proprietario> proprietarioOptional = proprietarioRepository.findById(id);
        if (proprietarioOptional == null || proprietarioOptional.isEmpty()) {
            throw new ProprietarioNaoEncontradoException();
        }
    }


    public void verificarCampoCnpj(ProprietarioDto proprietarioDto){
        String valor = proprietarioDto.getCnpj();
        valor = valor.replaceAll("[^0-9]", "");
        if (valor.length() != 14) {
            throw new CnpjInvalidoException();
        }
    }
}
