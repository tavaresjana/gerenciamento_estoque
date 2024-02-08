package com.gerenciamentoestoque.handler;

import com.gerenciamentoestoque.handler.exceptions.NomeInvalid;
import com.gerenciamentoestoque.handler.exceptions.PrecoInvalid;
import com.gerenciamentoestoque.handler.exceptions.ProdutoNotFound;
import com.gerenciamentoestoque.handler.exceptions.SkuInvalid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ProdutoNotFound.class)
    public ResponseEntity<StandardError> produtoNotFound(ProdutoNotFound produtoNotFound) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), produtoNotFound.getMessage());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(SkuInvalid.class)
    public ResponseEntity<StandardError> skuInvalid(SkuInvalid skuInvalid) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), skuInvalid.getMessage());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PrecoInvalid.class)
    public ResponseEntity<StandardError> precoInvalid(PrecoInvalid precoInvalid) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), precoInvalid.getMessage());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NomeInvalid.class)
    public ResponseEntity<StandardError> precoInvalid(NomeInvalid nomeInvalid) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), nomeInvalid.getMessage());
        return ResponseEntity.status(httpStatus).body(standardError);
    }
}
