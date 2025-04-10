package com.agrotis.agrotis_backend.application.service.impl;

import com.agrotis.agrotis_backend.application.dto.PessoaDTO;
import com.agrotis.agrotis_backend.application.mapper.PessoaMapper;
import com.agrotis.agrotis_backend.application.service.interfaces.PessoaService;
import com.agrotis.agrotis_backend.domain.model.Pessoa;
import com.agrotis.agrotis_backend.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.agrotis.agrotis_backend.application.mapper.PessoaMapper.toDTO;
import static com.agrotis.agrotis_backend.application.mapper.PessoaMapper.toEntity;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Optional<PessoaDTO> getPessoaById(Long id) {
        return pessoaRepository.findById(id)
                .map(PessoaMapper::toDTO);
    }

    @Override
    public PessoaDTO addPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = toEntity(pessoaDTO);
        pessoaRepository.save(pessoa);
        return PessoaMapper.toDTO(pessoa);
    }

    @Override
    public List<PessoaDTO> listarPessoas() {
        return pessoaRepository.findAll()
                .stream()
                .map(PessoaMapper::toDTO)
                .toList();
    }

    @Override
    public void deletePessoaById(Long id) {
        pessoaRepository.deleteById(id);
    }

    @Override
    public Optional<Pessoa> atualizarPessoa(Long id, PessoaDTO pessoaDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id)
                .map(pessoaAtualizada -> {
                    pessoaAtualizada.setNome(pessoaDTO.getNome());
                    pessoaAtualizada.setDataInicial(pessoaDTO.getDataInicial();
                    pessoaAtualizada.setDataFinal(pessoaDTO.getDataFinal());

                    pessoaAtualizada.setObservacoes(pessoaDTO.getObservacoes());

                    // TODO: adicionar laboratório e propriedade

                    return pessoaRepository.save(pessoaAtualizada);
                });


    }


}
