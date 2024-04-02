package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProprietarioDto;
import com.gerenciamentoestoque.mapper.ProprietarioMapper;
import com.gerenciamentoestoque.model.Proprietario;
import com.gerenciamentoestoque.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProprietarioService {

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @Autowired
    private ProprietarioMapper proprietarioMapper;

    public List<ProprietarioDto> buscarTodosProprietarios(){
        List<Proprietario> listaProprietario = proprietarioRepository.findAll();
        List<ProprietarioDto> listaProprietarioDto = listaProprietario.stream().map(proprietarioMapper::entidadeParaDto).collect(Collectors.toList());
        return listaProprietarioDto;
    }



}
