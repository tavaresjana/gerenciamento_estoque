package com.gerenciamentoestoque.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.dto.ProprietarioDto;
import com.gerenciamentoestoque.mapper.ProprietarioMapper;
import com.gerenciamentoestoque.model.Proprietario;
import com.gerenciamentoestoque.repository.ProprietarioRepository;
import com.gerenciamentoestoque.service.ProprietarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.gerenciamentoestoque.constants.Constants.PRODUTO_DTO;
import static com.gerenciamentoestoque.constants.Constants.PROPRIETARIO_DTO;
import static com.gerenciamentoestoque.constants.Constants.PROPRIETARIO_DTO2;
import static com.gerenciamentoestoque.constants.Constants.PROPRIETARIO_INATIVO_DTO;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProprietarioController.class)
public class ProprietarioControllerTeste {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProprietarioService proprietarioService;

    @MockBean
    ProprietarioMapper proprietarioMapper;

    @MockBean
    ProprietarioRepository proprietarioRepository;

    private ProprietarioDto proprietarioDto;

    @Test
    public void buscarTodosProprietarios() throws Exception{
        List<ProprietarioDto> listaProprietarioDto = new ArrayList<>();
        listaProprietarioDto.add(PROPRIETARIO_DTO);
        listaProprietarioDto.add(PROPRIETARIO_DTO2);

        when(proprietarioService.buscarTodosProprietarios()).thenReturn(listaProprietarioDto);
        mockMvc.perform(get("/proprietarios"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].nomeProprietario")
                        .value((PROPRIETARIO_DTO.getNomeProprietario())))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].nomeProprietario")
                        .value((PROPRIETARIO_DTO2.getNomeProprietario())));
        verify(proprietarioService, times(1)).buscarTodosProprietarios();
    }

    @Test
    public void buscarTodosProprietariosListaVazia() throws Exception {
        when(proprietarioService.buscarTodosProprietarios()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/proprietarios"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));
        verify(proprietarioService, times(1)).buscarTodosProprietarios();
    }



    @Test
    public void buscarTodosProprietariosAtivosListaVazia() throws Exception {
        when(proprietarioService.buscarProprietariosAtivos()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/proprietarios/ativos"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));
        verify(proprietarioService, times(1)).buscarProprietariosAtivos();
    }

    @Test
    public void buscarPorId() throws Exception{
        when(proprietarioService.buscarPorId(1L)).thenReturn(PROPRIETARIO_DTO);
        mockMvc.perform(get("/proprietarios/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
        verify(proprietarioService, times(1)).buscarPorId(1L);
    }

    @Test
    public void buscarPorCnpj() throws Exception{
        when(proprietarioService.buscarPorCnpj("88.462.269/0001-30")).thenReturn(PROPRIETARIO_DTO);
        mockMvc.perform(get("/proprietarios/cnpj").param("cnpj","88.462.269/0001-30"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cnpj").value(PROPRIETARIO_DTO.getCnpj()));
        verify(proprietarioService, times(1)).buscarPorCnpj("88.462.269/0001-30");
    }

    @Test
    public void atualizarProprietario() throws Exception{
        ProprietarioDto proprietarioDto = new ProprietarioDto(1L, "Joana Sukva", "88.462.269/0001-30", true);
        proprietarioDto.setNomeProprietario("Novo nome para Joana Sukva");

        when(proprietarioService.atualizarProprietario(any(ProprietarioDto.class))).thenReturn(proprietarioDto);
        mockMvc.perform(put("/proprietarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proprietarioDto)))
                .andExpect(status().isOk());
        verify(proprietarioService, times(1)).atualizarProprietario(any(ProprietarioDto.class));
    }

    @Test
    public void cadastrarProprietario() throws Exception{
        when(proprietarioService.cadastrarProprietario(PROPRIETARIO_DTO)).thenReturn(PROPRIETARIO_DTO);
        mockMvc.perform(post("/proprietarios")
                .content(objectMapper.writeValueAsString(PROPRIETARIO_DTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(proprietarioService, times(1)).cadastrarProprietario(PROPRIETARIO_DTO);
    }

    @Test
    public void desativarProprietario() throws  Exception{
        doNothing().when(proprietarioService).desativarProprietario(1L);
        mockMvc.perform(put("/proprietarios/desativar").param("id", "1"))
                .andExpect(status().isNoContent());
        verify(proprietarioService, times(1)).desativarProprietario(1L);
    }

    @Test
    public void reativarProprietario() throws  Exception{
        doNothing().when(proprietarioService).reativarProprietario(1L);
        mockMvc.perform(put("/proprietarios/reativar").param("id", "1"))
                .andExpect(status().isNoContent());
        verify(proprietarioService, times(1)).reativarProprietario(1L);
    }
    @Test
    public void buscarTodosProprietariosAtivos() throws Exception{
        List<ProprietarioDto> listaProprietarioDto = new ArrayList<>();
        listaProprietarioDto.add(PROPRIETARIO_DTO);
        listaProprietarioDto.add(PROPRIETARIO_DTO2);
        when(proprietarioService.buscarProprietariosAtivos()).thenReturn(listaProprietarioDto);
        mockMvc.perform(get("/proprietarios/ativos"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
        verify(proprietarioService, times(1)).buscarProprietariosAtivos();
    }

}



