package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.constants.Constants;
import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.handler.exceptions.ProdutoNaoEncontradoException;
import com.gerenciamentoestoque.mapper.ProdutoMapper;
import com.gerenciamentoestoque.model.Produto;
import com.gerenciamentoestoque.repository.ProdutoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.gerenciamentoestoque.constants.Constants.PRODUTO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.util.Assert.isInstanceOf;

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
        listaProduto.add(PRODUTO);
        listaProduto.add(Constants.PRODUTO2);

        //Criando lista de produto Dto
        List<ProdutoDto> listaProdutoDtoEsperada = new ArrayList<>();
        listaProdutoDtoEsperada.add(Constants.PRODUTO_DTO);
        listaProdutoDtoEsperada.add(Constants.PRODUTO_DTO2);

        // Mocks simulando retornos
        when(produtoRepository.findAll()).thenReturn(listaProduto);
        when(produtoMapper.entidadeParaDto(PRODUTO)).thenReturn(Constants.PRODUTO_DTO);
        when(produtoMapper.entidadeParaDto(Constants.PRODUTO2)).thenReturn(Constants.PRODUTO_DTO2);

        // Método sendo chamado
        List<ProdutoDto> buscarTodosProdutos = produtoService.buscarTodosProdutos();

        // Verificação
        assertEquals(listaProdutoDtoEsperada, buscarTodosProdutos);

    }

    @Test
    public void buscarPorSku(){
        when(produtoRepository.buscarPorSku("1287114")).thenReturn(PRODUTO);
        when(produtoMapper.entidadeParaDto(PRODUTO)).thenReturn(Constants.PRODUTO_DTO);
        ProdutoDto buscarPorSku = produtoService.buscarPorSku("1287114");
        assertEquals(buscarPorSku, PRODUTO_DTO);
    }

    @Test
    public void buscarPorSku_ProdutoNaoEncontradoException() throws Exception{
        String skuInvalido = "sku_invalido";
        doThrow(ProdutoNaoEncontradoException.class).when(validacoes).verificarSku("00000");
        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            produtoService.buscarPorSku("00000");
        });
    }
}
