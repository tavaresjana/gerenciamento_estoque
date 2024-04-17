package com.gerenciamentoestoque.handler.exceptions;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException(){
        super("Campo CPF inválido.");
    }
}
