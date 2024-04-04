package com.gerenciamentoestoque.controller;

import com.gerenciamentoestoque.dto.ProprietarioDto;
import com.gerenciamentoestoque.service.ProprietarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        ProprietarioDto proprietarioDto = proprietarioService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(proprietarioDto);
    }

    @PostMapping
    public ResponseEntity<ProprietarioDto> cadastrarProprietario(@RequestBody @Valid ProprietarioDto proprietarioDto){
        proprietarioDto.setAtivo(true);
        proprietarioService.cadastrarProprietario(proprietarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ProprietarioDto> atualizarProprietario(@PathVariable Long id, @RequestBody ProprietarioDto proprietarioDto){
        proprietarioService.atualizarProprietario(id, proprietarioDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/desativar{id}")
    public ResponseEntity<Void> desativarProprietario(@RequestParam(value = "id") @PathVariable Long id){
        proprietarioService.desativarProprietario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/reativar{id}")
    public ResponseEntity<Void> reativarProprietario(@RequestParam(value = "id") @PathVariable Long id){
        proprietarioService.reativarProprietario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value="/ativos")
    public ResponseEntity<List<ProprietarioDto>> buscarProprietariosAtivos(ProprietarioDto proprietarioDto){
        List<ProprietarioDto> listaProprietarioAtivoDto = proprietarioService.buscarProprietariosAtivos();
        return ResponseEntity.status(HttpStatus.OK).body(listaProprietarioAtivoDto);
    }

    @GetMapping(value="/cpf{cpf}")
    public ResponseEntity<ProprietarioDto> buscarPorCpf(@RequestParam(value="cpf") @PathVariable String cpf){
        ProprietarioDto proprietarioDto = proprietarioService.buscarPorCpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(proprietarioDto);
    }

    @GetMapping(value="/cnpj{cnpj}")
    public ResponseEntity<ProprietarioDto> buscarPorCnpj(@RequestParam(value="cnpj") @PathVariable String cnpj){
        ProprietarioDto proprietarioDto = proprietarioService.buscarPorCnpj(cnpj);
        return ResponseEntity.status(HttpStatus.OK).body(proprietarioDto);
    }
}
