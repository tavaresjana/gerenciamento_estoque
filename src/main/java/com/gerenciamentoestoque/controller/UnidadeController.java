package com.gerenciamentoestoque.controller;

import com.gerenciamentoestoque.dto.UnidadeDto;
import com.gerenciamentoestoque.service.UnidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/unidades")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @GetMapping
    public ResponseEntity<List<UnidadeDto>> buscarTodasUnidades() {
        List<UnidadeDto> listaUnidadeDto = unidadeService.buscarTodasUnidades();
        return ResponseEntity.status(HttpStatus.FOUND).body(listaUnidadeDto);
    }

    @PostMapping
    public ResponseEntity<UnidadeDto> cadastrarUnidade(@Valid @RequestBody UnidadeDto unidadeDto) {
        unidadeService.cadastrarUnidade(unidadeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
