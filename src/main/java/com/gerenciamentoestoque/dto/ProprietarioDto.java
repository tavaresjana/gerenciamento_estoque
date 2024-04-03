package com.gerenciamentoestoque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProprietarioDto {

    private Long id;
    private String nomeProprietario;
    private String cpf;
    private String cnpj;
    private Boolean ativo;
}
