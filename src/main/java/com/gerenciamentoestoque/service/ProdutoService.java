package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.model.Produto;
import com.gerenciamentoestoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

   public Produto cadastrarProduto(Produto produto){
        return produtoRepository.save(produto);
   }

    public Produto findById(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.get();
    }

    public void deleteProduto(Long id){
        produtoRepository.deleteById(id);
    }
}
