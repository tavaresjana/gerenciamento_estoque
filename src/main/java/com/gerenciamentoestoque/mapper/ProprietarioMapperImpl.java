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
        proprietarioDto.setCpf(proprietario.get().getCpf());
        proprietarioDto.setCnpj(proprietario.get().getCnpj());
        return proprietarioDto;
    }

    @Override
    public ProprietarioDto entidadeParaDto(Proprietario proprietario) {
        ProprietarioDto proprietarioDto = new ProprietarioDto();
        proprietarioDto.setId(proprietario.getId());
        proprietarioDto.setNomeProprietario(proprietario.getNomeProprietario());
        proprietarioDto.setCpf(proprietario.getCpf());
        proprietarioDto.setCnpj(proprietario.getCnpj());
        return proprietarioDto;
    }

    @Override
    public Proprietario dtoParaEntidade(ProprietarioDto proprietarioDto) {
        Proprietario proprietario = new Proprietario();
        proprietario.setId(proprietarioDto.getId());
        proprietario.setNomeProprietario(proprietarioDto.getNomeProprietario());
        proprietario.setCpf(proprietarioDto.getCpf());
        proprietario.setCnpj(proprietarioDto.getCnpj());
        return proprietario;
    }
}
