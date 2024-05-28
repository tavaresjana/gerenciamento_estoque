package com.gerenciamentoestoque.handler.exceptions;

public class NomeInvalidoException extends RuntimeException {
    public NomeInvalidoException(){
        super("Nome inválido. O nome do produto deverá ter entre 2 e 15 caracteres.");
    }

    public NomeInvalidoException(String msg){
        super(msg);
    }
}
