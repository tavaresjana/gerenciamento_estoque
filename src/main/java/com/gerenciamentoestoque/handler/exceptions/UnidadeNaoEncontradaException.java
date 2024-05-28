package com.gerenciamentoestoque.handler.exceptions;

public class UnidadeNaoEncontradaException extends RuntimeException{
    public UnidadeNaoEncontradaException(){
        super("Unidade não encontrada.");
    }
}
