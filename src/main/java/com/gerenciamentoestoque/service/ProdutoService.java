package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.handler.exceptions.PrecoInvalid;
import com.gerenciamentoestoque.handler.exceptions.ProdutoNotFound;
import com.gerenciamentoestoque.handler.exceptions.SkuInvalid;
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

    public List<ProdutoDto> findAll() {
        List<Produto> produtoList = produtoRepository.findAll();
        List<ProdutoDto> listProdutoDto = produtoList.stream().map(produtoMapper::entidadeParaDto).collect(Collectors.toList());
        return listProdutoDto;
    }

    public List<ProdutoDto> findBySku(String sku) {
        List<Produto> produtoList = produtoRepository.findBySku(sku);
        List<ProdutoDto> listProdutoDto = produtoList.stream().map(produtoMapper::entidadeParaDto).collect(Collectors.toList());
        return listProdutoDto;
    }

    public ProdutoDto cadastrarProduto(ProdutoDto produto) {
        if (verificarSkuExiste(produto) == true) {
            throw new SkuInvalid();
        }

        if (produto.getPreco().doubleValue() < 0.00 || produto.getPreco() == null) {
            throw new PrecoInvalid();
        }
        return produtoMapper.entidadeParaDto(produtoRepository.save(produtoMapper.dtoParaEntidade(produto)));
    }

    public Boolean verificarSkuExiste(ProdutoDto produtoDto) {
        if (!produtoRepository.findBySku(produtoDto.getSku()).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public ProdutoDto findById(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty() || produto == null) {
            throw new ProdutoNotFound();
        }
        Optional<ProdutoDto> produtoDto = Optional.ofNullable(produtoMapper.entidadeParaDtoOp(produto));
        return produtoDto.get();
    }

    public void deleteProduto(Long id) {
        if (produtoRepository.findById(id).isEmpty() || produtoRepository.findById(id) == null) {
            throw new ProdutoNotFound();
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoDto atualizarProduto(ProdutoDto produto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(produto.getId());
        if (optionalProduto.isEmpty() || optionalProduto == null) {
            throw new ProdutoNotFound();
        }
        Produto produtoEditado = optionalProduto.get();

        produtoEditado.setNomeProduto(produto.getNomeProduto());
        produtoEditado.setSku(produto.getSku());
        produtoEditado.setPreco(produto.getPreco());
        produtoRepository.save(produtoEditado);
        return produtoMapper.entidadeParaDto(produtoEditado);
    }
}
