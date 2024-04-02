package com.gerenciamentoestoque.controller;

import com.gerenciamentoestoque.dto.ProprietarioDto;
import com.gerenciamentoestoque.service.ProdutoService;
import com.gerenciamentoestoque.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/proprietarios")
public class ProprietarioController {

    @Autowired
    private ProprietarioService proprietarioService;

    @GetMapping
    public ResponseEntity<List<ProprietarioDto>> buscarTodosProprietarios(){
        List<ProprietarioDto> listaProprietarioDto = proprietarioService.buscarTodosProprietarios();
        return ResponseEntity.status(HttpStatus.OK).body(listaProprietarioDto);
    }

}
