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
import java.util.List;

import static com.gerenciamentoestoque.constants.Constants.PRODUTO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO2;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        listaProduto.add(PRODUTO);
        listaProduto.add(PRODUTO2);

        //Criando lista de produto Dto
        List<ProdutoDto> listaProdutoDtoEsperada = new ArrayList<>();
        listaProdutoDtoEsperada.add(Constants.PRODUTO_DTO);
        listaProdutoDtoEsperada.add(PRODUTO_DTO2);

        // Mocks simulando retornos
        when(produtoRepository.findAll()).thenReturn(listaProduto);
        when(produtoMapper.entidadeParaDto(PRODUTO)).thenReturn(Constants.PRODUTO_DTO);
        when(produtoMapper.entidadeParaDto(PRODUTO2)).thenReturn(PRODUTO_DTO2);

        // Método sendo chamado
        List<ProdutoDto> buscarTodosProdutos = produtoService.buscarTodosProdutos();

        // Verificação
        assertEquals(listaProdutoDtoEsperada, buscarTodosProdutos);

    }

    @Test
    public void buscarPorSkuTest() {
        when(produtoRepository.buscarPorSku("1287114")).thenReturn(PRODUTO);
        when(produtoMapper.entidadeParaDto(PRODUTO)).thenReturn(Constants.PRODUTO_DTO);
        ProdutoDto buscarPorSku = produtoService.buscarPorSku("1287114");
        assertEquals(buscarPorSku, PRODUTO_DTO);
    }

    @Test
    public void buscarPorSkuProdutoNaoEncontradoException() throws Exception {
        String skuInvalido = "sku_invalido";
        doThrow(ProdutoNaoEncontradoException.class).when(validacoes).verificarSku("00000");
        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            produtoService.buscarPorSku("00000");
        });
    }

    @Test
    public void buscarPorNomeTest() {
        List<Produto> listaProduto = new ArrayList<>();
        listaProduto.add(PRODUTO);
        listaProduto.add(PRODUTO2);

        List<ProdutoDto> listaProdutoDto = new ArrayList<>();
        listaProdutoDto.add(PRODUTO_DTO);
        listaProdutoDto.add(PRODUTO_DTO2);

        when(produtoRepository.buscarPorNome(PRODUTO.getNomeProduto())).thenReturn(listaProduto);

        when(produtoMapper.entidadeParaDto(PRODUTO)).thenReturn(PRODUTO_DTO);
        when(produtoMapper.entidadeParaDto(PRODUTO2)).thenReturn(PRODUTO_DTO2);

        List<ProdutoDto> buscarPorNome = produtoService.buscarPorNome(PRODUTO.getNomeProduto());
        assertEquals(listaProdutoDto.get(0), buscarPorNome.get(0));
        assertEquals(listaProdutoDto.get(1), buscarPorNome.get(1));
        Assertions.assertThat(buscarPorNome).isNotEmpty();
    }

    @Test
    public void buscarPorNomeProdutoNaoEncontradoException() {
        List<Produto> listaProdutoVazia = new ArrayList<>();
        when(produtoRepository.buscarPorNome("a")).thenReturn(listaProdutoVazia);
        Assertions.assertThatExceptionOfType(ProdutoNaoEncontradoException.class)
                .isThrownBy(() -> produtoService.buscarPorNome("a"));
        verify(produtoRepository, times(1)).buscarPorNome("a");
    }
}
