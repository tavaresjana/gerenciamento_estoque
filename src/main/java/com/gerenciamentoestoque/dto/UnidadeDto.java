package com.gerenciamentoestoque.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeDto {
    private Long id;
    private String nomeUnidade;
    private ProprietarioDto proprietario;
}
