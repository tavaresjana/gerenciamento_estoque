package com.gerenciamentoestoque.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "O campo preço é obrigatório.")
    @PositiveOrZero(message = "O preço deve ser um número positivo.")
    private BigDecimal preco;

}
