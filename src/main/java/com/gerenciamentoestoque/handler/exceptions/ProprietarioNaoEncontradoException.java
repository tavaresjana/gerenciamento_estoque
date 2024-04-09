package com.gerenciamentoestoque.handler.exceptions;

public class ProprietarioNaoEncontradoException extends RuntimeException{
    public ProprietarioNaoEncontradoException(){
        super("Proprietario não encontrado.");
    }
}
