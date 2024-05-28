package com.gerenciamentoestoque.controller;

import com.gerenciamentoestoque.dto.UnidadeDto;
import com.gerenciamentoestoque.service.UnidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<UnidadeDto> buscarPorId(@PathVariable Long id) {
        UnidadeDto unidade = unidadeService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(unidade);
    }

    @PutMapping
    public ResponseEntity<UnidadeDto> atualizarUnidade(@RequestBody UnidadeDto unidadeDto){
        unidadeService.atualizarUnidade(unidadeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarUnidade(@PathVariable Long id) {
        unidadeService.deletarUnidade(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/proprietario/{proprietarioId}")
    public ResponseEntity<List<UnidadeDto>> buscarUnidadesPorProprietario(@PathVariable Long proprietarioId) {
        List<UnidadeDto> unidades = unidadeService.buscarUnidadesPorProprietario(proprietarioId);
        return ResponseEntity.ok(unidades);
    }

}
