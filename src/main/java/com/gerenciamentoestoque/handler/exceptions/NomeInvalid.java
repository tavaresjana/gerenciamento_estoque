package com.gerenciamentoestoque.handler.exceptions;

public class NomeInvalid extends RuntimeException {
    public NomeInvalid(){
        super("Nome inválido. O nome do produto deverá ter entre 2 e 15 caracteres.");
    }
}
