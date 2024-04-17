package com.gerenciamentoestoque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProprietarioDto {

    private Long id;
    private String nomeProprietario;
    private String cnpj;
    private Boolean ativo;
}
