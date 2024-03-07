package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.handler.exceptions.ProdutoNaoEncontradoException;
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

    @Autowired
    private Validacoes validacoes;

    public List<ProdutoDto> buscarTodosProdutos() {
        List<Produto> listaProduto = produtoRepository.findAll();
        List<ProdutoDto> listaProdutoDto = listaProduto.stream().map(produtoMapper::entidadeParaDto).collect(Collectors.toList());
        return listaProdutoDto;
    }

    public ProdutoDto buscarPorSku(String sku) {
        Produto produto = produtoRepository.buscarPorSku(sku);
        validacoes.verificarSku(sku);
        ProdutoDto produtoDto = produtoMapper.entidadeParaDto(produto);
        return produtoDto;
    }

    public List<ProdutoDto> buscarPorNome(String nomeProduto) {
        List<Produto> listaProduto = produtoRepository.buscarPorNome(nomeProduto);
        if (listaProduto.isEmpty()) {
            throw new ProdutoNaoEncontradoException();
        }
        List<ProdutoDto> listaProdutoDto = listaProduto.stream().map(produtoMapper::entidadeParaDto).collect(Collectors.toList());
        return listaProdutoDto;
    }

    public ProdutoDto cadastrarProduto(ProdutoDto produtoDto) {
        validacoes.verificarValidacoes(produtoDto);
        return produtoMapper.entidadeParaDto(produtoRepository.save(produtoMapper.dtoParaEntidade(produtoDto)));
    }

    public ProdutoDto buscarPorId(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        validacoes.verificarId(id);
        Optional<ProdutoDto> produtoDto = Optional.ofNullable(produtoMapper.entidadeParaDtoOp(produto));
        return produtoDto.get();
    }

    public void deletarProduto(Long id) {
        validacoes.verificarId(id);
        produtoRepository.deleteById(id);
    }

    public ProdutoDto atualizarProduto(ProdutoDto produto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(produto.getId());
        validacoes.verificarId(produto.getId());
        Produto produtoEditado = optionalProduto.get();

        produtoEditado.setNomeProduto(produto.getNomeProduto());
        produtoEditado.setSku(produto.getSku());
        produtoEditado.setPreco(produto.getPreco());
        produtoRepository.save(produtoEditado);
        return produtoMapper.entidadeParaDto(produtoEditado);
    }
}