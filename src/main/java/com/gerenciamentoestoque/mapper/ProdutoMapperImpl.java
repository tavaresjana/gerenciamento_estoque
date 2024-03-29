package com.gerenciamentoestoque.mapper;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.model.Produto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProdutoMapperImpl implements ProdutoMapper {

    public ProdutoDto entidadeParaDtoOp(Optional<Produto> produto){
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setId(produto.get().getId());
        produtoDto.setNomeProduto(produto.get().getNomeProduto());
        produtoDto.setSku(produto.get().getSku());
        produtoDto.setPreco(produto.get().getPreco());
        return produtoDto;
    }

    @Override
    public ProdutoDto entidadeParaDto(Produto produto) {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setId(produto.getId());
        produtoDto.setNomeProduto(produto.getNomeProduto());
        produtoDto.setSku(produto.getSku());
        produtoDto.setPreco(produto.getPreco());
        return produtoDto;
    }

    @Override
    public Produto dtoParaEntidade(ProdutoDto produtoDto) {
        Produto produto = new Produto();
        produto.setId(produtoDto.getId());
        produto.setNomeProduto(produtoDto.getNomeProduto());
        produto.setSku(produtoDto.getSku());
        produto.setPreco(produtoDto.getPreco());
        return produto;
    }

}
