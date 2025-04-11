package com.agrotis.agrotis_backend.application.service.impl;

import com.agrotis.agrotis_backend.application.dto.AddPessoaDTO;
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
import java.util.Optional;

import static com.agrotis.agrotis_backend.application.mapper.Mapper.toEntity;

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
    public Optional<PessoaDTO> getPessoaById(Long id) {
        return pessoaRepository.findById(id)
                .map(Mapper::toDTO);
    }

    @Override
    public PessoaDTO addPessoa(AddPessoaDTO addPessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(addPessoaDTO.getNome());
        pessoa.setDataInicial(addPessoaDTO.getDataInicial());
        pessoa.setDataFinal(addPessoaDTO.getDataFinal());


        Propriedade propriedade = propriedadeRepository.findById(addPessoaDTO.getIdPropriedade())
                .orElseThrow(() -> new EntityNotFoundException("Não existe a Propriedade com ID " + addPessoaDTO.getIdPropriedade()));
        Laboratorio laboratorio = laboratorioRepository.findById(addPessoaDTO.getIdLaboratorio())
                .orElseThrow(() -> new EntityNotFoundException("Não existe o Laboratório com ID " + addPessoaDTO.getIdLaboratorio()));

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
    public PessoaDTO atualizarPessoa(Long id, PessoaDTO pessoaDTO) {

        Pessoa pessoaAtualizada = pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa com ID " + id + " não encontrado"));

        pessoaAtualizada.setNome(pessoaDTO.getNome());
        pessoaAtualizada.setDataInicial(pessoaDTO.getDataInicial());
        pessoaAtualizada.setDataFinal(pessoaDTO.getDataFinal());

        pessoaAtualizada.setObservacoes(pessoaDTO.getObservacoes());

        if (pessoaDTO.getInfosPropriedade() != null) {
            Propriedade propriedade = new Propriedade();
            propriedade.setNome(pessoaDTO.getInfosPropriedade().getNome());
            pessoaAtualizada.setInfosPropriedade(propriedade);
        }

        if (pessoaDTO.getLaboratorio() != null) {
            Laboratorio laboratorio = new Laboratorio();
            laboratorio.setNome(pessoaDTO.getLaboratorio().getNome());
            pessoaAtualizada.setLaboratorio(laboratorio);
        }

        pessoaRepository.save(pessoaAtualizada);
        return Mapper.toDTO(pessoaAtualizada);

    }


}
