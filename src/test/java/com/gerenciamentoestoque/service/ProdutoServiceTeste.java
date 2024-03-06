package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.constants.Constants;
import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.mapper.ProdutoMapper;
import com.gerenciamentoestoque.model.Produto;
import com.gerenciamentoestoque.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTeste {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private Validacoes validacoes;

    @Mock
    private ProdutoMapper produtoMapper;



    @Test
    public void buscarTodosProdutos() {
        //Criando lista de produto entidade
        List<Produto> listaProduto = new ArrayList<>();
        listaProduto.add(Constants.PRODUTO);
        listaProduto.add(Constants.PRODUTO2);

        //Criando lista de produto Dto
        List<ProdutoDto> listaProdutoDtoEsperada = new ArrayList<>();
        listaProdutoDtoEsperada.add(Constants.PRODUTO_DTO);
        listaProdutoDtoEsperada.add(Constants.PRODUTO_DTO2);

        // Mocks simulando retornos
        when(produtoRepository.findAll()).thenReturn(listaProduto);
        when(produtoMapper.entidadeParaDto(Constants.PRODUTO)).thenReturn(Constants.PRODUTO_DTO);
        when(produtoMapper.entidadeParaDto(Constants.PRODUTO2)).thenReturn(Constants.PRODUTO_DTO2);

        // Método sendo chamado
        List<ProdutoDto> buscarTodosProdutos = produtoService.buscarTodosProdutos();

        // Verificação
        assertEquals(listaProdutoDtoEsperada, buscarTodosProdutos);

    }

}
