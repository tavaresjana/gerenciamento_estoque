package com.gerenciamentoestoque.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciamentoestoque.service.ProdutoService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
public class ProdutoControllerTest {

    @InjectMocks
    ProdutoController produtoController;

    @Mock
    ProdutoService produtoService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

}
