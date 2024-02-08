package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.handler.exceptions.CampoVazio;
import com.gerenciamentoestoque.handler.exceptions.NomeInvalid;
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

    public ProdutoDto cadastrarProduto(ProdutoDto produtoDto) {
        verificaValidacoes(produtoDto);
        return produtoMapper.entidadeParaDto(produtoRepository.save(produtoMapper.dtoParaEntidade(produtoDto)));
    }

    public void verificarNome(ProdutoDto produtoDto) {
        if (produtoDto.getNomeProduto().length() < 2) {
            throw new NomeInvalid();
        }
    }

    public void verificarPreco(ProdutoDto produtoDto) {
        if (produtoDto.getPreco().doubleValue() < 0.00) {
            throw new PrecoInvalid();
        }
    }

    public void verificaCampoVazio(ProdutoDto produtoDto) {
        if (produtoDto.getPreco() == null || produtoDto.getPreco().toString().isEmpty()
                || produtoDto.getNomeProduto() == null || produtoDto.getNomeProduto().isEmpty()
                || produtoDto.getSku() == null || produtoDto.getSku().isEmpty()) {
            throw new CampoVazio();
        }
    }

    public void verificarSkuExiste(ProdutoDto produtoDto) {
        if (!produtoRepository.findBySku(produtoDto.getSku()).isEmpty()) {
            throw new SkuInvalid();
        }
    }

    public void verificaValidacoes(ProdutoDto produtoDto) {
        verificaCampoVazio(produtoDto);
        verificarSkuExiste(produtoDto);
        verificarNome(produtoDto);
        verificarPreco(produtoDto);
    }

    public void verificaId(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty() || produtoOptional == null) {
            throw new ProdutoNotFound();
        }
    }

    public ProdutoDto findById(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        verificaId(id);
        Optional<ProdutoDto> produtoDto = Optional.ofNullable(produtoMapper.entidadeParaDtoOp(produto));
        return produtoDto.get();
    }

    public void deleteProduto(Long id) {
        verificaId(id);
        produtoRepository.deleteById(id);
    }

    public ProdutoDto atualizarProduto(ProdutoDto produto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(produto.getId());
        verificaId(produto.getId());
        Produto produtoEditado = optionalProduto.get();

        produtoEditado.setNomeProduto(produto.getNomeProduto());
        produtoEditado.setSku(produto.getSku());
        produtoEditado.setPreco(produto.getPreco());
        produtoRepository.save(produtoEditado);
        return produtoMapper.entidadeParaDto(produtoEditado);
    }
}
