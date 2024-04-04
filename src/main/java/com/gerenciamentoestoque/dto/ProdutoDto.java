package com.gerenciamentoestoque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeProduto;
    private String sku;
    private BigDecimal preco;
}
