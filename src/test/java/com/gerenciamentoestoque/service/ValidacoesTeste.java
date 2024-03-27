package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.constants.Constants;
import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.handler.exceptions.NomeInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.PrecoInvalidoException;
import com.gerenciamentoestoque.handler.exceptions.ProdutoNaoEncontradoException;
import com.gerenciamentoestoque.handler.exceptions.SkuInvalidoException;
import com.gerenciamentoestoque.mapper.ProdutoMapper;
import com.gerenciamentoestoque.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.gerenciamentoestoque.constants.Constants.PRODUTO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO_NOME_INVALIDO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO_OP;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO_PRECO_INVALIDO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_OP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidacoesTeste {

    @InjectMocks
    private Validacoes validacoes;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoMapper produtoMapper;

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
    public void verificarSkuExisteException(){
        when(produtoRepository.buscarPorSku("1287114")).thenReturn(PRODUTO);
        assertThrows(SkuInvalidoException.class, () -> {
            validacoes.verificarSkuExiste(PRODUTO_DTO);
        });
    }

    @Test
    public void verificarSkuNaoEncontradoException(){
        when(produtoRepository.buscarPorSku(any())).thenThrow(ProdutoNaoEncontradoException.class);
        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            validacoes.verificarSku(any());
        });
    }


}
