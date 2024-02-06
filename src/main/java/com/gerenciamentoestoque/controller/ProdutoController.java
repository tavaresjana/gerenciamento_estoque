package com.gerenciamentoestoque.controller;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.handler.exceptions.ProdutoNotFound;
import com.gerenciamentoestoque.model.Produto;
import com.gerenciamentoestoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> findAll(){
        List<ProdutoDto> listProdutoDto = produtoService.findAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(listProdutoDto);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrarProduto(@RequestBody ProdutoDto produtoDto){
        produtoService.cadastrarProduto(produtoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable Long id){
        ProdutoDto produto = produtoService.findById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(produto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id){
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDto produto){
        produtoService.atualizarProduto(produto);
        return ResponseEntity.ok().build();
    }

}
