package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.constants.Constants;
import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.handler.exceptions.NomeInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.PrecoInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.ProdutoNaoEncontradoException;
import com.gerenciamentoestoque.handler.exceptions.SkuInvalidoException;
import com.gerenciamentoestoque.mapper.ProdutoMapper;
import com.gerenciamentoestoque.model.Produto;
import com.gerenciamentoestoque.repository.ProdutoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gerenciamentoestoque.constants.Constants.PRODUTO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO2;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO2;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO_NOME_INVALIDO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO_OP;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO_PRECO_INVALIDO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_OP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
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

    @Test
    public void buscarPorIdTest() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(PRODUTO_OP));
        when(produtoMapper.entidadeParaDtoOp(Optional.of(PRODUTO_OP))).thenReturn(PRODUTO_DTO_OP);
        ProdutoDto buscarPorId = produtoService.buscarPorId(1L);
        assertEquals(buscarPorId, PRODUTO_DTO_OP);
    }

    @Test
    public void buscarPorIdProdutoNaoEncontradoException() {
        doThrow(ProdutoNaoEncontradoException.class).when(validacoes).verificarId(1L);
        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            produtoService.buscarPorId(1L);
        });
    }

    @Test
    public void deletarProdutoTest() {
        doNothing().when(produtoRepository).deleteById(1L);
        produtoService.deletarProduto(1L);
        verify(produtoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deletarProdutoNaoEncontradoException() {
        doThrow(ProdutoNaoEncontradoException.class).when(validacoes).verificarId(1L);
        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            produtoService.deletarProduto(1L);
        });
    }


    @Test
    public void cadastrarProduto() {
        ProdutoDto produtoDtoEntrada = Constants.PRODUTO_DTO;
        Produto produtoEntidade = Constants.PRODUTO;
        ProdutoDto produtoDtoEsperado = Constants.PRODUTO_DTO;

        when(produtoMapper.dtoParaEntidade(produtoDtoEntrada)).thenReturn(produtoEntidade);
        when(produtoRepository.save(produtoEntidade)).thenReturn(produtoEntidade);
        when(produtoMapper.entidadeParaDto(produtoEntidade)).thenReturn(produtoDtoEsperado);

        ProdutoDto resultado = produtoService.cadastrarProduto(produtoDtoEntrada);

        assertNotNull(resultado);
        assertEquals(produtoDtoEsperado, resultado);
        verify(validacoes).verificarValidacoes(produtoDtoEntrada);
        verify(produtoRepository).save(produtoEntidade);
        verify(produtoMapper).entidadeParaDto(produtoEntidade);
    }

    @Test
    public void cadastrarProdutoSkuExistenteException(){
        doThrow(SkuInvalidoException.class).when(validacoes).verificarValidacoes(PRODUTO_DTO);
        assertThrows(SkuInvalidoException.class, () -> {
            produtoService.cadastrarProduto(PRODUTO_DTO);
        });
    }
    @Test
    public void cadastrarProdutoNomeInvalidoException(){
        doThrow(NomeInvalidoException.class).when(validacoes).verificarValidacoes(PRODUTO_DTO_NOME_INVALIDO);
        assertThrows(NomeInvalidoException.class, () -> {
            produtoService.cadastrarProduto(PRODUTO_DTO_NOME_INVALIDO);
        });
    }

    @Test
    public void cadastrarProdutoPrecoInvalidoException(){
        doThrow(PrecoInvalidoException.class).when(validacoes).verificarValidacoes(PRODUTO_DTO_PRECO_INVALIDO);
        assertThrows(PrecoInvalidoException.class, () -> {
            produtoService.cadastrarProduto(PRODUTO_DTO_PRECO_INVALIDO);
        });
    }

    @Test
    public void atualizarProdutoTest(){
        Produto produto = Constants.PRODUTO;
        ProdutoDto produtoDto = Constants.PRODUTO_DTO;

        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setId(produto.getId());
        produtoAtualizado.setNomeProduto("Nome do produto atualizado");
        produtoAtualizado.setSku("SKU atualizado");
        produtoAtualizado.setPreco(BigDecimal.valueOf(3.0)); // Preço atualizado

        when(produtoRepository.findById(produtoDto.getId())).thenReturn(Optional.of(produtoAtualizado));
        when(produtoMapper.entidadeParaDto(produtoAtualizado)).thenReturn(produtoDto);

        ProdutoDto resultado = produtoService.atualizarProduto(produtoDto);

        assertEquals(produtoDto, resultado);
    }



}
