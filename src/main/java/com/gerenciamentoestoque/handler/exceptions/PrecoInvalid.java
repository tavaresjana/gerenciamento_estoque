package com.gerenciamentoestoque.handler.exceptions;

public class PrecoInvalid extends RuntimeException {

    public PrecoInvalid() {
        super("Preço inválido. O preço deve ser um número positivo.");
    }
}
