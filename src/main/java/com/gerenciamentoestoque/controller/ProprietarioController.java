package com.gerenciamentoestoque.controller;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.dto.ProprietarioDto;
import com.gerenciamentoestoque.service.ProprietarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping(value="/{id}")
    public ResponseEntity<ProprietarioDto> buscarPorId(@PathVariable Long id){
        ProprietarioDto proprietario = proprietarioService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(proprietario);
    }

    @PostMapping
    public ResponseEntity<ProprietarioDto> cadastrarProprietario(@RequestBody @Valid ProprietarioDto proprietarioDto){
        proprietarioService.cadastrarProprietario(proprietarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
