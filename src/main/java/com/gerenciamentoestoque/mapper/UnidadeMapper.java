package com.gerenciamentoestoque.mapper;

import com.gerenciamentoestoque.dto.UnidadeDto;
import com.gerenciamentoestoque.model.Unidade;

import java.util.Optional;

public interface UnidadeMapper {
    UnidadeDto entidadeParaDtoOp(Optional<Unidade> unidade);
    UnidadeDto entidadeParaDto(Unidade unidade);

    Unidade dtoParaEntidade(UnidadeDto unidade);
}
