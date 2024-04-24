package com.gerenciamentoestoque.constants;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.dto.ProprietarioDto;
import com.gerenciamentoestoque.model.Produto;
import com.gerenciamentoestoque.model.Proprietario;

import java.math.BigDecimal;
import java.util.Optional;

public class Constants {
    public static final Produto PRODUTO = new Produto(1L,"Nome do produto","1287114", BigDecimal.valueOf(2.00));
    public static final Produto PRODUTO2 = new Produto(2L,"Segundo novo produto","1287115", BigDecimal.valueOf(3.00));
    public static final Produto PRODUTO_OP = new Produto(3L, "Produto Optional", "1287116", BigDecimal.valueOf(4.00));
    public static final ProdutoDto PRODUTO_DTO = new ProdutoDto(1L,"Nome do produto","1287114", BigDecimal.valueOf(2.00));
    public static final ProdutoDto PRODUTO_DTO2 = new ProdutoDto(2L,"Segundo novo produto","1287115", BigDecimal.valueOf(3.00));
    public static final ProdutoDto PRODUTO_DTO_NOME_INVALIDO = new ProdutoDto(4L,"N","1287116", BigDecimal.valueOf(7.00));
    public static final ProdutoDto PRODUTO_DTO_PRECO_INVALIDO = new ProdutoDto(5L,"Nome do produto","1287117", BigDecimal.valueOf(-7.00));
    public static final ProdutoDto PRODUTO_DTO_OP = new ProdutoDto(3L, "Produto Optional", "1287116", BigDecimal.valueOf(4.00));


    public static final Proprietario PROPRIETARIO = new Proprietario(1L, "João Sukva", "88.462.269/0001-30", true);
    public static final ProprietarioDto PROPRIETARIO_DTO = new ProprietarioDto(1L, "João Sukva", "88.462.269/0001-30", true);
    public static final Proprietario PROPRIETARIO2 = new Proprietario(2L, "Antonio Sukva", "89.304.694/0001-64", true);
    public static final ProprietarioDto PROPRIETARIO_DTO2 = new ProprietarioDto(2L, "Antonio Sukva", "89.304.694/0001-64", true);

    public static final ProprietarioDto PROPRIETARIO_INATIVO_DTO = new ProprietarioDto(1L, "Joana Januário", "88.462.269/0001-30", false);}
