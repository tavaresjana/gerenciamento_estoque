package com.gerenciamentoestoque.mapper;

import com.gerenciamentoestoque.dto.UnidadeDto;
import com.gerenciamentoestoque.model.Unidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UnidadeMapperImpl implements UnidadeMapper{

    @Autowired
    private ProprietarioMapper proprietarioMapper;

    @Override
    public UnidadeDto entidadeParaDtoOp(Optional<Unidade> unidade) {
        UnidadeDto unidadeDto = new UnidadeDto();
        unidadeDto.setId(unidade.get().getId());
        unidadeDto.setNomeUnidade(unidade.get().getNomeUnidade());
        unidadeDto.setProprietario(proprietarioMapper.entidadeParaDto(unidade.get().getProprietario()));
        return unidadeDto;
    }

    @Override
    public UnidadeDto entidadeParaDto(Unidade unidade) {
        UnidadeDto unidadeDto = new UnidadeDto();
        unidadeDto.setId(unidade.getId());
        unidadeDto.setNomeUnidade(unidade.getNomeUnidade());
        unidadeDto.setProprietario(proprietarioMapper.entidadeParaDto(unidade.getProprietario()));
        return unidadeDto;
    }

    @Override
    public Unidade dtoParaEntidade(UnidadeDto unidadeDto) {
        Unidade unidade = new Unidade();
        unidade.setId(unidadeDto.getId());
        unidade.setNomeUnidade(unidadeDto.getNomeUnidade());
        unidade.setProprietario(proprietarioMapper.dtoParaEntidade(unidadeDto.getProprietario()));
        return unidade;
    }
}
