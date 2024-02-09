package com.gerenciamentoestoque.handler.exceptions;

public class SkuInvalidoException extends RuntimeException {
    public SkuInvalidoException(){
        super("SKU inválido. Informe um SKU válido.");
    }
}
