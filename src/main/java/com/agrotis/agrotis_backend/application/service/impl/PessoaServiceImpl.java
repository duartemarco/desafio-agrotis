package com.agrotis.agrotis_backend.application.service.impl;

import com.agrotis.agrotis_backend.application.dto.PessoaRequestDTO;
import com.agrotis.agrotis_backend.application.dto.PessoaDTO;
import com.agrotis.agrotis_backend.application.mapper.Mapper;
import com.agrotis.agrotis_backend.application.service.interfaces.PessoaService;
import com.agrotis.agrotis_backend.domain.model.Laboratorio;
import com.agrotis.agrotis_backend.domain.model.Pessoa;
import com.agrotis.agrotis_backend.domain.model.Propriedade;
import com.agrotis.agrotis_backend.repository.LaboratorioRepository;
import com.agrotis.agrotis_backend.repository.PessoaRepository;
import com.agrotis.agrotis_backend.repository.PropriedadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;
    private final LaboratorioRepository laboratorioRepository;
    private final PropriedadeRepository propriedadeRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository, LaboratorioRepository laboratorioRepository, PropriedadeRepository propriedadeRepository) {
        this.pessoaRepository = pessoaRepository;
        this.laboratorioRepository = laboratorioRepository;
        this.propriedadeRepository = propriedadeRepository;
    }

    @Override
    public PessoaDTO getPessoaById(Long id) {
        return pessoaRepository.findById(id)
                .map(Mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrada Pessoa com ID " + id));
    }

    @Override
    public PessoaDTO addPessoa(PessoaRequestDTO addPessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(addPessoaDTO.getNome());
        pessoa.setDataInicial(addPessoaDTO.getDataInicial());
        pessoa.setDataFinal(addPessoaDTO.getDataFinal());
        pessoa.setObservacoes(addPessoaDTO.getObservacoes());

        Propriedade propriedade = propriedadeRepository.findById(addPessoaDTO.getIdPropriedade())
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrada Propriedade com ID " + addPessoaDTO.getIdPropriedade()));
        Laboratorio laboratorio = laboratorioRepository.findById(addPessoaDTO.getIdLaboratorio())
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado Laboratório com ID " + addPessoaDTO.getIdLaboratorio()));

        pessoa.setInfosPropriedade(propriedade);
        pessoa.setLaboratorio(laboratorio);

        pessoaRepository.save(pessoa);
        return Mapper.toDTO(pessoa);
    }

    @Override
    public List<PessoaDTO> listarPessoas() {
        return pessoaRepository.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public void deletePessoaById(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pessoa com ID " + id + " não encontrado");
        }
        pessoaRepository.deleteById(id);
    }

    @Override
    public PessoaDTO atualizarPessoa(Long id, PessoaRequestDTO pessoaDTO) {

        Pessoa pessoaAtualizada = pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa com ID " + id + " não encontrado"));

        pessoaAtualizada.setNome(pessoaDTO.getNome());
        pessoaAtualizada.setDataInicial(pessoaDTO.getDataInicial());
        pessoaAtualizada.setDataFinal(pessoaDTO.getDataFinal());

        pessoaAtualizada.setObservacoes(pessoaDTO.getObservacoes());

        if (pessoaDTO.getIdPropriedade() != null) {
            Propriedade propriedade = propriedadeRepository.findById(pessoaDTO.getIdPropriedade())
                    .orElseThrow(() -> new EntityNotFoundException("Propriedade com ID " + pessoaDTO.getIdPropriedade() + " não encontrada"));
            pessoaAtualizada.setInfosPropriedade(propriedade);
        }

        if (pessoaDTO.getIdLaboratorio() != null) {
            Laboratorio laboratorio = laboratorioRepository.findById(pessoaDTO.getIdLaboratorio())
                    .orElseThrow(() -> new EntityNotFoundException("Laboratório com ID " + pessoaDTO.getIdLaboratorio() + " não encontrado"));
            pessoaAtualizada.setLaboratorio(laboratorio);
        }

        pessoaRepository.save(pessoaAtualizada);
        return Mapper.toDTO(pessoaAtualizada);

    }


}
