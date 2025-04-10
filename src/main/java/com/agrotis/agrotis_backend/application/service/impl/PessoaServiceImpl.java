package com.agrotis.agrotis_backend.application.service.impl;

import com.agrotis.agrotis_backend.application.dto.PessoaDTO;
import com.agrotis.agrotis_backend.application.mapper.Mapper;
import com.agrotis.agrotis_backend.application.service.exception.LaboratorioNotFoundException;
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
    public PessoaDTO addPessoa(PessoaDTO pessoaDTO) {

        Long idPropriedade = pessoaDTO.getInfosPropriedade().getId();
        Long idLaboratorio = pessoaDTO.getLaboratorio().getId();

        Pessoa pessoa = toEntity(pessoaDTO);

        Propriedade propriedade = propriedadeRepository.findById(idPropriedade)
                .orElseThrow(() -> new EntityNotFoundException("Não existe a propriedade com ID " + idPropriedade));
        Laboratorio laboratorio = laboratorioRepository.findById(idLaboratorio)
                .orElseThrow(LaboratorioNotFoundException::new);

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
        pessoaRepository.deleteById(id);
    }

    @Override
    public Optional<PessoaDTO> atualizarPessoa(Long id, PessoaDTO pessoaDTO) {
        return pessoaRepository.findById(id)
                .map(pessoaAtualizada -> {
                    pessoaAtualizada.setNome(pessoaDTO.getNome());
                    pessoaAtualizada.setDataInicial(pessoaDTO.getDataInicial());
                    pessoaAtualizada.setDataFinal(pessoaDTO.getDataFinal());

                    pessoaAtualizada.setObservacoes(pessoaDTO.getObservacoes());

                    if (pessoaDTO.getInfosPropriedade() != null) {
                        Propriedade propriedade = new Propriedade();
                        propriedade.setId(pessoaDTO.getInfosPropriedade().getId());
                        propriedade.setNome(pessoaDTO.getInfosPropriedade().getNome());
                        pessoaAtualizada.setInfosPropriedade(propriedade);
                    }

                    if (pessoaDTO.getLaboratorio() != null) {
                        Laboratorio laboratorio = new Laboratorio();
                        laboratorio.setId(pessoaDTO.getLaboratorio().getId());
                        laboratorio.setNome(pessoaDTO.getLaboratorio().getNome());
                        pessoaAtualizada.setLaboratorio(laboratorio);
                    }

                    Pessoa pessoa = toEntity(pessoaDTO);
                    pessoaRepository.save(pessoa);
                    return Mapper.toDTO(pessoa);
                });


    }


}
