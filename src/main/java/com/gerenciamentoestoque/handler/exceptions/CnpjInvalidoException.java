package com.gerenciamentoestoque.handler.exceptions;

public class CnpjInvalidoException extends RuntimeException{
    public CnpjInvalidoException(){
        super("Campo CNPJ inválido.");
    }
}
