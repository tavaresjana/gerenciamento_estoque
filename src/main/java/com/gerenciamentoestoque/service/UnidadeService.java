package com.gerenciamentoestoque.service;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.dto.UnidadeDto;
import com.gerenciamentoestoque.mapper.UnidadeMapper;
import com.gerenciamentoestoque.model.Produto;
import com.gerenciamentoestoque.model.Unidade;
import com.gerenciamentoestoque.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private UnidadeMapper unidadeMapper;

    public List<UnidadeDto> buscarTodasUnidades() {
        List<Unidade> listaUnidade = unidadeRepository.findAll();
        List<UnidadeDto> listaUnidadeDto = listaUnidade.stream().map(unidadeMapper::entidadeParaDto).collect(Collectors.toList());
        return listaUnidadeDto;
    }

    public UnidadeDto cadastrarUnidade(UnidadeDto unidadeDto) {
        return unidadeMapper.entidadeParaDto(unidadeRepository.save(unidadeMapper.dtoParaEntidade(unidadeDto)));
    }
}
