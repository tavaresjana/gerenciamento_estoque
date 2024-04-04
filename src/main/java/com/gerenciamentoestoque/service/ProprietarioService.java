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

    public List<ProprietarioDto> buscarTodosProprietarios() {
        List<Proprietario> listaProprietario = proprietarioRepository.findAll();
        List<ProprietarioDto> listaProprietarioDto = listaProprietario.stream().map(proprietarioMapper::entidadeParaDto).collect(Collectors.toList());
        return listaProprietarioDto;
    }

    public ProprietarioDto buscarPorId(Long id) {
        Optional<Proprietario> proprietario = proprietarioRepository.findById(id);
        Optional<ProprietarioDto> proprietarioDto = Optional.ofNullable(proprietarioMapper.entidadeParaDtoOp(proprietario));
        return proprietarioDto.get();
    }

    public ProprietarioDto cadastrarProprietario(ProprietarioDto proprietarioDto) {
        return proprietarioMapper.entidadeParaDto(proprietarioRepository.save((proprietarioMapper.dtoParaEntidade(proprietarioDto))));
    }

    public ProprietarioDto atualizarProprietario(ProprietarioDto proprietarioDto) {
        Optional<Proprietario> optionalProprietario = proprietarioRepository.findById(proprietarioDto.getId());

        Proprietario proprietarioEditado = optionalProprietario.get();

        proprietarioEditado.setNomeProprietario(proprietarioDto.getNomeProprietario());
        proprietarioEditado.setCpf(proprietarioDto.getCpf());
        proprietarioEditado.setCnpj(proprietarioDto.getCnpj());

        proprietarioRepository.save(proprietarioEditado);
        return proprietarioMapper.entidadeParaDto(proprietarioEditado);
    }

    public void desativarProprietario(Long id) {
        Optional<Proprietario> optionalProprietario = proprietarioRepository.findById(id);
        Proprietario proprietarioEditado = optionalProprietario.get();
        proprietarioEditado.setAtivo(false);
        proprietarioRepository.save(proprietarioEditado);
    }

    public void reativarProprietario(Long id) {
        Optional<Proprietario> optionalProprietario = proprietarioRepository.findById(id);
        Proprietario proprietarioEditado = optionalProprietario.get();
        proprietarioEditado.setAtivo(true);
        proprietarioRepository.save(proprietarioEditado);
    }

    public List<ProprietarioDto> buscarProprietariosAtivos() {
        List<Proprietario> listaProprietarios = proprietarioRepository.findAll();
        List<Proprietario> listaProprietariosAtivos = listaProprietarios.stream()
                .filter(Proprietario::getAtivo)
                .collect(Collectors.toList());

        List<ProprietarioDto> listaProprietariosAtivosDto = listaProprietariosAtivos.stream()
                .map(proprietarioMapper::entidadeParaDto)
                .collect(Collectors.toList());
        return listaProprietariosAtivosDto;
    }

    public ProprietarioDto buscarPorCpf(String cpf) {
        Proprietario proprietario = proprietarioRepository.buscarPorCpf(formatarCPFouCNPJ(cpf));
        ProprietarioDto proprietarioDto = proprietarioMapper.entidadeParaDto(proprietario);
        return proprietarioDto;
    }

    public ProprietarioDto buscarPorCnpj(String cnpj) {
        Proprietario proprietario = proprietarioRepository.buscarPorCnpj(formatarCPFouCNPJ(cnpj));
        ProprietarioDto proprietarioDto = proprietarioMapper.entidadeParaDto(proprietario);
        return proprietarioDto;
    }

    public static String formatarCPFouCNPJ(String valor) {
        valor = valor.replaceAll("[^0-9]", "");
        if (valor.length() == 11) {
            return valor.substring(0, 3) + "." + valor.substring(3, 6) + "." +
                    valor.substring(6, 9) + "-" + valor.substring(9);

        } else if (valor.length() == 14) {
            return valor.substring(0, 2) + "." + valor.substring(2, 5) + "." +
                    valor.substring(5, 8) + "/" + valor.substring(8, 12) + "-" +
                    valor.substring(12);
        } else {
            return null;
            //add verificação de tamanho invalido p cpf
        }
    }

}
