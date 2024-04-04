package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProprietarioDto;
import com.gerenciamentoestoque.mapper.ProprietarioMapper;
import com.gerenciamentoestoque.model.Proprietario;
import com.gerenciamentoestoque.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public ProprietarioDto buscarPorId(Long id){
        Optional<Proprietario> proprietario = proprietarioRepository.findById(id);
        Optional<ProprietarioDto> proprietarioDto = Optional.ofNullable(proprietarioMapper.entidadeParaDtoOp(proprietario));
        return proprietarioDto.get();
    }

    public ProprietarioDto cadastrarProprietario(ProprietarioDto proprietarioDto){
        return proprietarioMapper.entidadeParaDto(proprietarioRepository.save((proprietarioMapper.dtoParaEntidade(proprietarioDto))));
    }

    public ProprietarioDto atualizarProprietario(Long id, ProprietarioDto proprietarioDto){
        Optional<Proprietario> optionalProprietario = proprietarioRepository.findById(id);

        Proprietario proprietarioEditado = optionalProprietario.get();

        proprietarioEditado.setNomeProprietario(proprietarioDto.getNomeProprietario());
        proprietarioEditado.setCpf(proprietarioDto.getCpf());
        proprietarioEditado.setCnpj(proprietarioDto.getCnpj());

        proprietarioRepository.save(proprietarioEditado);
        return proprietarioMapper.entidadeParaDto(proprietarioEditado);
    }

    public ProprietarioDto desativarProprietario(Long id){
        Optional<Proprietario> optionalProprietario = proprietarioRepository.findById(id);
        Proprietario proprietarioEditado = optionalProprietario.get();
        proprietarioEditado.setAtivo(false);
        proprietarioRepository.save(proprietarioEditado);
        return proprietarioMapper.entidadeParaDto(proprietarioEditado);
    }

    public ProprietarioDto reativarProprietario(Long id){
        Optional<Proprietario> optionalProprietario = proprietarioRepository.findById(id);
        Proprietario proprietarioEditado = optionalProprietario.get();
        proprietarioEditado.setAtivo(true);
        proprietarioRepository.save(proprietarioEditado);
        return proprietarioMapper.entidadeParaDto(proprietarioEditado);
    }

    public List<ProprietarioDto> buscarProprietariosAtivos(){
        List<Proprietario> listaProprietarios = proprietarioRepository.findAll();

        List<Proprietario> listaProprietariosAtivos = listaProprietarios.stream()
                .filter(Proprietario::getAtivo)
                .collect(Collectors.toList());

        List<ProprietarioDto> listaProprietariosAtivosDto = listaProprietariosAtivos.stream().map(proprietarioMapper::entidadeParaDto).collect(Collectors.toList());

        return listaProprietariosAtivosDto;
    }

}
