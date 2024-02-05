package com.gerenciamentoestoque.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String nomeProduto;
    @NotNull
    private int sku;
    @NotNull
    private BigDecimal preco;

}
