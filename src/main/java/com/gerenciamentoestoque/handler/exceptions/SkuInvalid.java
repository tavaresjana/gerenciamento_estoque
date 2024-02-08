package com.gerenciamentoestoque.handler.exceptions;

public class SkuInvalid extends RuntimeException {
    public SkuInvalid(){
        super("SKU inválido. Informe um SKU válido.");
    }
}
