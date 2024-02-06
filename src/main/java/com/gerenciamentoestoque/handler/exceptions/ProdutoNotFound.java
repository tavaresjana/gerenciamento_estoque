package com.gerenciamentoestoque.handler.exceptions;

public class ProdutoNotFound extends RuntimeException{

    public ProdutoNotFound(){
        super("Produto não encontrado");
    }
}
