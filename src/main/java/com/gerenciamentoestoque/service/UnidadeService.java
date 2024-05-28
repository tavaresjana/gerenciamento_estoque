package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.dto.UnidadeDto;
import com.gerenciamentoestoque.mapper.ProprietarioMapper;
import com.gerenciamentoestoque.mapper.UnidadeMapper;
import com.gerenciamentoestoque.model.Produto;
import com.gerenciamentoestoque.model.Unidade;
import com.gerenciamentoestoque.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private UnidadeMapper unidadeMapper;

    @Autowired
    private ProprietarioMapper proprietarioMapper;

    public List<UnidadeDto> buscarTodasUnidades() {
        List<Unidade> listaUnidade = unidadeRepository.findAll();
        List<UnidadeDto> listaUnidadeDto = listaUnidade.stream().map(unidadeMapper::entidadeParaDto).collect(Collectors.toList());
        return listaUnidadeDto;
    }

    public UnidadeDto cadastrarUnidade(UnidadeDto unidadeDto) {
        return unidadeMapper.entidadeParaDto(unidadeRepository.save(unidadeMapper.dtoParaEntidade(unidadeDto)));
    }

    public UnidadeDto buscarPorId(Long id) {
        Optional<Unidade> unidade = unidadeRepository.findById(id);
        Optional<UnidadeDto> unidadeDto = Optional.ofNullable(unidadeMapper.entidadeParaDtoOp(unidade));
        return unidadeDto.get();
    }

    public UnidadeDto atualizarUnidade(UnidadeDto unidade) {
        Optional<Unidade> optionalUnidade = unidadeRepository.findById(unidade.getId());
        Unidade unidadeEditada = optionalUnidade.get();
        unidadeEditada.setNomeUnidade(unidade.getNomeUnidade());
        unidadeEditada.setProprietario(proprietarioMapper.dtoParaEntidade(unidade.getProprietario()));
        unidadeRepository.save(unidadeEditada);
        return unidadeMapper.entidadeParaDto(unidadeEditada);
    }

    public void deletarUnidade(Long id) {
        unidadeRepository.deleteById(id);
    }

    public List<UnidadeDto> buscarUnidadesPorProprietario(Long proprietarioId) {
        List<Unidade> unidades = unidadeRepository.findByProprietarioId(proprietarioId);
        List<UnidadeDto> listaUnidadesPorProprietario = unidades.stream()
                .map(unidadeMapper::entidadeParaDto)
                .collect(Collectors.toList());
        return listaUnidadesPorProprietario;
    }
}
