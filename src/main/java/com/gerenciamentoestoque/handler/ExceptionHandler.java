package com.gerenciamentoestoque.handler;

import com.gerenciamentoestoque.handler.exceptions.ProdutoNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ProdutoNotFound.class)
    public ResponseEntity<StandardError> produtoNotFound(ProdutoNotFound produtoNotFound){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), produtoNotFound.getMessage());
        return ResponseEntity.status(httpStatus).body(standardError);
    }
}
