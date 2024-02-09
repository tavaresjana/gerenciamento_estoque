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

    public List<ProdutoDto> buscarTodosProdutos() {
        List<Produto> listaProduto = produtoRepository.findAll();
        List<ProdutoDto> listaProdutoDto = listaProduto.stream().map(produtoMapper::entidadeParaDto).collect(Collectors.toList());
        return listaProdutoDto;
    }

    public List<ProdutoDto> buscarPorSku(String sku) {
        List<Produto> listaProduto = produtoRepository.buscarPorSku(sku);
        List<ProdutoDto> listaProdutoDto = listaProduto.stream().map(produtoMapper::entidadeParaDto).collect(Collectors.toList());
        return listaProdutoDto;
    }

    public List<ProdutoDto> buscarPorNome(String nomeProduto) {
        List<Produto> listaProduto = produtoRepository.buscarPorNome(nomeProduto);
        if (listaProduto.isEmpty()) {
            throw new ProdutoNotFound();
        }
        List<ProdutoDto> listaProdutoDto = listaProduto.stream().map(produtoMapper::entidadeParaDto).collect(Collectors.toList());
        return listaProdutoDto;
    }

    public ProdutoDto cadastrarProduto(ProdutoDto produtoDto) {
        verificarValidacoes(produtoDto);
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

    public void verificarCampoVazio(ProdutoDto produtoDto) {
        if (produtoDto.getPreco() == null || produtoDto.getPreco().toString().isEmpty()
                || produtoDto.getNomeProduto() == null || produtoDto.getNomeProduto().isEmpty()
                || produtoDto.getSku() == null || produtoDto.getSku().isEmpty()) {
            throw new CampoVazio();
        }
    }

    public void verificarSkuExiste(ProdutoDto produtoDto) {
        if (!produtoRepository.buscarPorSku(produtoDto.getSku()).isEmpty()) {
            throw new SkuInvalid();
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
            throw new ProdutoNotFound();
        }
    }

    public ProdutoDto buscarPorId(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        verificarId(id);
        Optional<ProdutoDto> produtoDto = Optional.ofNullable(produtoMapper.entidadeParaDtoOp(produto));
        return produtoDto.get();
    }

    public void deletarProduto(Long id) {
        verificarId(id);
        produtoRepository.deleteById(id);
    }

    public ProdutoDto atualizarProduto(ProdutoDto produto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(produto.getId());
        verificarId(produto.getId());
        Produto produtoEditado = optionalProduto.get();

        produtoEditado.setNomeProduto(produto.getNomeProduto());
        produtoEditado.setSku(produto.getSku());
        produtoEditado.setPreco(produto.getPreco());
        produtoRepository.save(produtoEditado);
        return produtoMapper.entidadeParaDto(produtoEditado);
    }
}
