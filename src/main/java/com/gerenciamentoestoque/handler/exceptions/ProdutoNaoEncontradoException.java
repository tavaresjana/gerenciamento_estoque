package com.gerenciamentoestoque.handler.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException{
    public ProdutoNaoEncontradoException(){
        super("Produto não encontrado.");
    }
}
