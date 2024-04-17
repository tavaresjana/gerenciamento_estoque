package com.gerenciamentoestoque.mapper;

import com.gerenciamentoestoque.dto.ProdutoDto;
import com.gerenciamentoestoque.dto.ProprietarioDto;
import com.gerenciamentoestoque.model.Produto;
import com.gerenciamentoestoque.model.Proprietario;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProprietarioMapperImpl implements ProprietarioMapper{
    @Override
    public ProprietarioDto entidadeParaDtoOp(Optional<Proprietario> proprietario) {
        ProprietarioDto proprietarioDto = new ProprietarioDto();
        proprietarioDto.setId(proprietario.get().getId());
        proprietarioDto.setNomeProprietario(proprietario.get().getNomeProprietario());
        proprietarioDto.setCnpj(proprietario.get().getCnpj());
        proprietarioDto.setAtivo(proprietario.get().getAtivo());
        return proprietarioDto;
    }

    @Override
    public ProprietarioDto entidadeParaDto(Proprietario proprietario) {
        ProprietarioDto proprietarioDto = new ProprietarioDto();
        proprietarioDto.setId(proprietario.getId());
        proprietarioDto.setNomeProprietario(proprietario.getNomeProprietario());
        proprietarioDto.setCnpj(proprietario.getCnpj());
        proprietarioDto.setAtivo(proprietario.getAtivo());
        return proprietarioDto;
    }

    @Override
    public Proprietario dtoParaEntidade(ProprietarioDto proprietarioDto) {
        Proprietario proprietario = new Proprietario();
        proprietario.setId(proprietarioDto.getId());
        proprietario.setNomeProprietario(proprietarioDto.getNomeProprietario());
        proprietario.setCnpj(proprietarioDto.getCnpj());
        proprietario.setAtivo(proprietarioDto.getAtivo());
        return proprietario;
    }
}
