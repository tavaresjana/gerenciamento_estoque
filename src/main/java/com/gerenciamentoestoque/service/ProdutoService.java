package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.mapper.ProdutoMapper;
import com.gerenciamentoestoque.model.Produto;
import com.gerenciamentoestoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    public List<ProdutoDto> findAll(){
        List<Produto> produtoList = produtoRepository.findAll();
        List<ProdutoDto> listProdutoDto = produtoList.stream().map(produtoMapper::entidadeParaDto).collect(Collectors.toList());
        return listProdutoDto;
    }

   public ProdutoDto cadastrarProduto(ProdutoDto produto){
        return produtoMapper.entidadeParaDto(produtoRepository.save(produtoMapper.dtoParaEntidade(produto)));
   }

    public ProdutoDto findById(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        Optional<ProdutoDto> produtoDto = Optional.ofNullable(produtoMapper.entidadeParaDtoOp(produto));
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
