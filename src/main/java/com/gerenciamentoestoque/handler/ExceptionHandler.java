package com.gerenciamentoestoque.handler;

import com.gerenciamentoestoque.handler.exceptions.CampoVazioException;
import com.gerenciamentoestoque.handler.exceptions.NomeInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.PrecoInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.ProdutoNaoEncontradoException;
import com.gerenciamentoestoque.handler.exceptions.ProprietarioNaoEncontradoException;
import com.gerenciamentoestoque.handler.exceptions.SkuInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<StandardError> produtoNaoEncontradoException(ProdutoNaoEncontradoException produtoNaoEncontradoException) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), produtoNaoEncontradoException.getMessage());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SkuInvalidoException.class)
    public ResponseEntity<StandardError> skuInvalidoException(SkuInvalidoException skuInvalidoException) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), skuInvalidoException.getMessage());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PrecoInvalidoException.class)
    public ResponseEntity<StandardError> precoInvalidoException(PrecoInvalidoException precoInvalidoException) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), precoInvalidoException.getMessage());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NomeInvalidoException.class)
    public ResponseEntity<StandardError> nomeInvalidoException(NomeInvalidoException nomeInvalidoException) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), nomeInvalidoException.getMessage());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CampoVazioException.class)
    public ResponseEntity<StandardError> campoVazioException(CampoVazioException campoVazioException) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), campoVazioException.getMessage());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ProprietarioNaoEncontradoException.class)
    public ResponseEntity<StandardError> proprietarioNaoEncontradoException(ProprietarioNaoEncontradoException proprietarioNaoEncontradoException) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), proprietarioNaoEncontradoException.getMessage());
        return ResponseEntity.status(httpStatus).body(standardError);
    }
}
