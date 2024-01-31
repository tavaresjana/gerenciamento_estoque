package com.gerenciamentoestoque.mapper;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.model.Produto;

import java.util.Optional;

public interface ProdutoMapper {

    ProdutoDto entidadeParaDto(Optional<Produto> produto);

    Produto dtoParaEntidade(ProdutoDto produtoDto);
}
