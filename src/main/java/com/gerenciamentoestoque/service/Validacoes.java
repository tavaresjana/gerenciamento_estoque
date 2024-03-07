package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.handler.exceptions.CampoVazioException;
import com.gerenciamentoestoque.handler.exceptions.NomeInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.PrecoInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.ProdutoNaoEncontradoException;
import com.gerenciamentoestoque.handler.exceptions.SkuInvalidoException;
import com.gerenciamentoestoque.mapper.ProdutoMapper;
import com.gerenciamentoestoque.model.Produto;
import com.gerenciamentoestoque.repository.ProdutoRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Validacoes {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;
    public void verificarNome(ProdutoDto produtoDto) {
        if (produtoDto.getNomeProduto().length() < 2) {
            throw new NomeInvalidoException();
        }
    }

    public void verificarPreco(ProdutoDto produtoDto) {
        if (produtoDto.getPreco().doubleValue() < 0.00) {
            throw new PrecoInvalidoException();
        }
    }

    public void verificarCampoVazio(ProdutoDto produtoDto) {
        if (produtoDto.getPreco() == null || produtoDto.getPreco().toString().isEmpty()
                || produtoDto.getNomeProduto() == null || produtoDto.getNomeProduto().isEmpty()
                || produtoDto.getSku() == null || produtoDto.getSku().isEmpty()) {
            throw new CampoVazioException();
        }
    }

    public void verificarSkuExiste(ProdutoDto produtoDto) {
        if (produtoRepository.buscarPorSku(produtoDto.getSku()) != null) {
            throw new SkuInvalidoException();
        }
    }

    public void verificarValidacoes(ProdutoDto produtoDto) {
        verificarCampoVazio(produtoDto);
        verificarSkuExiste(produtoDto);
        verificarNome(produtoDto);
        verificarPreco(produtoDto);
    }

    public void verificarId(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty() || produtoOptional == null) {
            throw new ProdutoNaoEncontradoException();
        }
    }

    public void verificarSku(String sku){
        Produto skuProduto = produtoRepository.buscarPorSku(sku);
        if(skuProduto == null){
            throw new ProdutoNaoEncontradoException();
        }
    }
}
