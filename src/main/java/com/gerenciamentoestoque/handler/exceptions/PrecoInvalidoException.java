package com.gerenciamentoestoque.handler.exceptions;

public class PrecoInvalidoException extends RuntimeException {
    public PrecoInvalidoException() {
        super("Preço inválido. O preço deve ser um número positivo.");
    }
}
