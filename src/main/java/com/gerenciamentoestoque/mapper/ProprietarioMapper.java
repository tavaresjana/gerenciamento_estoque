package com.gerenciamentoestoque.mapper;

import com.gerenciamentoestoque.dto.ProprietarioDto;
import com.gerenciamentoestoque.model.Proprietario;

import java.util.Optional;

public interface ProprietarioMapper {
    ProprietarioDto entidadeParaDtoOp(Optional<Proprietario> proprietario);
    ProprietarioDto entidadeParaDto(Proprietario proprietario);

    Proprietario dtoParaEntidade(ProprietarioDto proprietarioDto);
}
