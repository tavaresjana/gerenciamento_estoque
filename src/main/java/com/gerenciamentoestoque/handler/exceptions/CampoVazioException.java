package com.gerenciamentoestoque.handler.exceptions;

public class CampoVazioException extends RuntimeException {
    public CampoVazioException(){
        super("Há campos não preenchidos.");
    }
}
