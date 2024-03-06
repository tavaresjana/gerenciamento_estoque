package com.gerenciamentoestoque.constants;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.model.Produto;

import java.math.BigDecimal;

public class Constants {
    public static final Produto PRODUTO = new Produto(1L,"Nome do produto","1287114", BigDecimal.valueOf(2.00));
    public static final Produto PRODUTO2 = new Produto(1L,"Segundo novo produto","1287115", BigDecimal.valueOf(3.00));

    public static final ProdutoDto PRODUTO_DTO = new ProdutoDto(1L,"Nome do produto","1287114", BigDecimal.valueOf(2.00));
    public static final ProdutoDto PRODUTO_DTO2 = new ProdutoDto(1L,"Segundo novo produto","1287115", BigDecimal.valueOf(3.00));
}
