package com.gerenciamentoestoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    @NotBlank(message = "O campo nome do produto é obrigatório.")
    @Length(min = 2, max = 15, message = "O nome do produto deverá ter entre 2 e 15 caracteres.")
    private String nomeProduto;

    @NotNull
    @NotBlank(message = "O campo sku é obrigatório.")
    private String sku;

    private BigDecimal preco;

}
