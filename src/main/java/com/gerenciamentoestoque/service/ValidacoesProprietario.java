package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.dto.ProprietarioDto;
import com.gerenciamentoestoque.handler.exceptions.CnpjInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.CpfInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.NomeInvalidoException;
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

    public void verificarCampoCpf(ProprietarioDto proprietarioDto) {
        String valor = proprietarioDto.getCpf();
        valor = valor.replaceAll("[^0-9]", "");
        if (valor.length() != 11) {
          throw new CpfInvalidoException();
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
