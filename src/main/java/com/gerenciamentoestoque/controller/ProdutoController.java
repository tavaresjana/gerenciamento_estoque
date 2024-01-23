package com.gerenciamentoestoque.controller;

import com.gerenciamentoestoque.model.Produto;
import com.gerenciamentoestoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        List<Produto> listaDeProduto = produtoService.findAll();
        return ResponseEntity.ok().body(listaDeProduto);
    }
}
