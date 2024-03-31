package com.gerenciamentoestoque.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciamentoestoque.constants.Constants;
import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.mapper.ProdutoMapper;
import com.gerenciamentoestoque.repository.ProdutoRepository;
import com.gerenciamentoestoque.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO;
import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO2;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProdutoController.class)
public class ProdutoControllerTeste {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProdutoService produtoService;

    @MockBean
    ProdutoMapper produtoMapper;

    @MockBean
    ProdutoRepository produtoRepository;

    private ProdutoDto produtoDto;



    @Test
    public void buscarTodosProdutos() throws Exception {
        List<ProdutoDto> listaProdutoDtoEsperada = new ArrayList<>();
        listaProdutoDtoEsperada.add(Constants.PRODUTO_DTO);
        listaProdutoDtoEsperada.add(PRODUTO_DTO2);

        when(produtoService.buscarTodosProdutos()).thenReturn(listaProdutoDtoEsperada);

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("[0].nomeProduto").value((PRODUTO_DTO.getNomeProduto())))
                .andExpect(jsonPath("[1].nomeProduto").value((PRODUTO_DTO2.getNomeProduto())));
        verify(produtoService, times(1)).buscarTodosProdutos();
    }

    @Test
    public void buscarTodosProdutoListaVazia() throws Exception {
        when(produtoService.buscarTodosProdutos()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/produtos"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", hasSize(0)));
        verify(produtoService, times(1)).buscarTodosProdutos();
    }

    @Test
    public void buscarPorSku() throws Exception {
        when(produtoService.buscarPorSku("1287114")).thenReturn(PRODUTO_DTO);
        mockMvc.perform(get("/produtos/sku").param("sku", "1287114"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.sku").value(PRODUTO_DTO.getSku()));
        verify(produtoService, times(1)).buscarPorSku("1287114");
    }

    @Test
    public void buscarPorNome() throws Exception {
        when(produtoService.buscarPorNome("Segundo novo produto")).thenReturn(Collections.singletonList(PRODUTO_DTO2));
        mockMvc.perform(get("/produtos/nomeProduto").param("nomeProduto", "Segundo novo produto"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].nomeProduto").value(PRODUTO_DTO2.getNomeProduto()));
        verify(produtoService, times(1)).buscarPorNome("Segundo novo produto");
    }

    @Test
    public void buscarPorId() throws Exception {
        when(produtoService.buscarPorId(1L)).thenReturn(PRODUTO_DTO);
        mockMvc.perform(get("/produtos/{id}", 1L))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(1));
        verify(produtoService, times(1)).buscarPorId(1L);
    }

    @Test
    public void deletarProduto() throws Exception {
        doNothing().when(produtoService).deletarProduto(1L);
        mockMvc.perform(delete("/produtos/{id}", 1L))
                .andExpect(status().isNoContent());
        verify(produtoService, times(1)).deletarProduto(1L);
    }

    @Test
    public void cadastrarProduto() throws Exception {
        when(produtoService.cadastrarProduto(PRODUTO_DTO)).thenReturn(PRODUTO_DTO);
        mockMvc.perform(post("/produtos")
                        .content(objectMapper.writeValueAsString(PRODUTO_DTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isCreated());
        verify(produtoService, times(1)).cadastrarProduto(PRODUTO_DTO);
    }

    @Test
    public void atualizarProdutos() throws Exception {
        ProdutoDto produtoDto = new ProdutoDto(1L,"Nome do produto","1287114", BigDecimal.valueOf(2.00));
        produtoDto.setNomeProduto("Novo nome atualizado");
        produtoDto.setPreco(BigDecimal.valueOf(100.0));

        when(produtoService.atualizarProduto(any(ProdutoDto.class))).thenReturn(produtoDto);
        mockMvc.perform(put("/produtos/{id}", produtoDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produtoDto)))
                .andExpect(status().isOk());
        verify(produtoService, times(1)).atualizarProduto(any(ProdutoDto.class));
    }


}
