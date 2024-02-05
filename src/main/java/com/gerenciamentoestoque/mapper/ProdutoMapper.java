package com.gerenciamentoestoque.mapper;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.model.Produto;

import java.util.Optional;

public interface ProdutoMapper {

    ProdutoDto entidadeParaDtoOp(Optional<Produto> produto);
    ProdutoDto entidadeParaDto(Produto produto);

    Produto dtoParaEntidade(ProdutoDto produtoDto);
}
