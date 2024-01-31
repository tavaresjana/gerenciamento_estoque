package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.mapper.ProdutoMapper;
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

    @Autowired
    private ProdutoMapper produtoMapper;

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

   public Produto cadastrarProduto(Produto produto){
        return produtoRepository.save(produto);
   }

    public ProdutoDto findById(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        Optional<ProdutoDto> produtoDto = Optional.ofNullable(produtoMapper.entidadeParaDto(produto));
        return produtoDto.get();
    }

    public void deleteProduto(Long id){
        produtoRepository.deleteById(id);
    }

    public Produto atualizarProduto(Produto produto){
        Optional<Produto> optionalProduto = produtoRepository.findById(produto.getId());
        Produto produtoEditado = optionalProduto.get();

        produtoEditado.setNomeProduto(produto.getNomeProduto());
        produtoEditado.setSku(produto.getSku());
        produtoEditado.setPreco(produto.getPreco());
        produtoRepository.save(produtoEditado);
        return produtoEditado;
    }
}
