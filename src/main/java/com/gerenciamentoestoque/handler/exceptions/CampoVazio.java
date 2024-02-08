package com.gerenciamentoestoque.handler.exceptions;

public class CampoVazio extends RuntimeException {
    public CampoVazio(){
        super("Há campos não preenchidos.");
    }
}
