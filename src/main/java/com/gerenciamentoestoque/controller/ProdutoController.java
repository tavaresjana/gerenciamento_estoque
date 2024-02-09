package com.gerenciamentoestoque.controller;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.model.Produto;
import com.gerenciamentoestoque.service.ProdutoService;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> buscarTodosProdutos() {
        List<ProdutoDto> listaProdutoDto = produtoService.buscarTodosProdutos();
        return ResponseEntity.status(HttpStatus.FOUND).body(listaProdutoDto);
    }

    @GetMapping(value = "/sku{sku}")
    public ResponseEntity<List<ProdutoDto>> buscarPorSku(@RequestParam(value = "sku") @PathVariable String sku) {
        List<ProdutoDto> listaProdutoDto = produtoService.buscarPorSku(sku);
        return ResponseEntity.status(HttpStatus.FOUND).body(listaProdutoDto);
    }

    @GetMapping(value = "/nomeProduto{nomeProduto}")
    public ResponseEntity<List<ProdutoDto>> buscarPorNome(@RequestParam(value = "nomeProduto") @PathVariable String nomeProduto) {
        List<ProdutoDto> listaProdutoDto = produtoService.buscarPorNome(nomeProduto);
        return ResponseEntity.status(HttpStatus.FOUND).body(listaProdutoDto);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrarProduto(@Valid @RequestBody ProdutoDto produtoDto) {
        produtoService.cadastrarProduto(produtoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDto> buscarPorId(@PathVariable Long id) {
        ProdutoDto produto = produtoService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(produto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDto produto) {
        produtoService.atualizarProduto(produto);
        return ResponseEntity.ok().build();
    }

}
