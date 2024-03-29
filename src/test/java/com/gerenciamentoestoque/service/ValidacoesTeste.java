package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.handler.exceptions.CampoVazioException;
import com.gerenciamentoestoque.handler.exceptions.NomeInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.PrecoInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.ProdutoNaoEncontradoException;
import com.gerenciamentoestoque.handler.exceptions.SkuInvalidoException;
import com.gerenciamentoestoque.mapper.ProdutoMapper;
import com.gerenciamentoestoque.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.gerenciamentoestoque.constants.Constants.PRODUTO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO_NOME_INVALIDO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO_PRECO_INVALIDO;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidacoesTeste {

    @InjectMocks
    private Validacoes validacoes;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoMapper produtoMapper;
    private ProdutoDto produtoDto;

    @BeforeEach()
    public void produtoDto(){
        produtoDto = new ProdutoDto(1L,"ProdutoDto","1287120", BigDecimal.valueOf(15.00));
    }

    @Test
    public void verificarNomeTeste(){
        assertThrows(NomeInvalidoException.class, () -> {
            validacoes.verificarNome(PRODUTO_DTO_NOME_INVALIDO);
        });
    }

    @Test
    public void verificarPrecoTeste(){
        assertThrows(PrecoInvalidoException.class, () -> {
            validacoes.verificarPreco(PRODUTO_DTO_PRECO_INVALIDO);
        });
    }

    @Test
    public void verificarSkuExisteTeste(){
        when(produtoRepository.buscarPorSku("1287114")).thenReturn(PRODUTO);
        assertThrows(SkuInvalidoException.class, () -> {
            validacoes.verificarSkuExiste(PRODUTO_DTO);
        });
    }

    @Test
    public void verificarSkuNaoEncontradoTeste(){
        when(produtoRepository.buscarPorSku(any())).thenReturn(null);
        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            validacoes.verificarSku(any());
        });
    }

    @Test
    public void verificarIdNullNaoEncontradoTeste(){
        when(produtoRepository.findById(any())).thenReturn(null);
        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            validacoes.verificarId(any());
        });
    }

    @Test
    public void verificarCampoVazioPrecoTeste(){
        produtoDto.setPreco(null);
        assertThrows(CampoVazioException.class, () -> {
            validacoes.verificarCampoVazio(produtoDto);
        });
        assertNull(produtoDto.getPreco());
    }

    @Test
    public void verificarCampoVazioNomeNullTeste(){
        produtoDto.setNomeProduto(null);
        assertThrows(CampoVazioException.class, () -> {
            validacoes.verificarCampoVazio(produtoDto);
        });
        assertNull(produtoDto.getNomeProduto());
    }

    @Test
    public void verificarCampoVazioNomeTeste(){
        produtoDto.setNomeProduto("");
        assertThrows(CampoVazioException.class, () -> {
            validacoes.verificarCampoVazio(produtoDto);
        });
        assertTrue(produtoDto.getNomeProduto().isEmpty());
    }

    @Test
    public void verificarCampoVazioSkuNullTeste(){
        produtoDto.setSku(null);
        assertThrows(CampoVazioException.class, () -> {
            validacoes.verificarCampoVazio(produtoDto);
        });
        assertNull(produtoDto.getSku());
    }

    @Test
    public void verificarCampoVazioSkuTeste(){
        produtoDto.setSku("");
        assertThrows(CampoVazioException.class, () -> {
            validacoes.verificarCampoVazio(produtoDto);
        });
        assertTrue(produtoDto.getSku().isEmpty());
    }

//verificarValidacoes
    @Test
    public void verificarValidacoesPrecoNullTeste(){
        produtoDto.setPreco(null);
        assertThrows(CampoVazioException.class, () -> {
            validacoes.verificarValidacoes(produtoDto);
        });
        assertNull(produtoDto.getPreco());
    }

    @Test
    public void verificarValidacoesSkuExisteTeste(){
        when(produtoRepository.buscarPorSku("1287114")).thenReturn(PRODUTO);
        assertThrows(SkuInvalidoException.class, () -> {
            validacoes.verificarValidacoes(PRODUTO_DTO);
        });
    }

    @Test
    public void verificarValidacoesNomeInvalidoTeste(){
        assertThrows(NomeInvalidoException.class, () -> {
            validacoes.verificarValidacoes(PRODUTO_DTO_NOME_INVALIDO);
        });
    }

    @Test
    public void verificarValidacoesPrecoNegativoTeste(){
        assertThrows(PrecoInvalidoException.class, () -> {
            validacoes.verificarValidacoes(PRODUTO_DTO_PRECO_INVALIDO);
        });
    }

}
