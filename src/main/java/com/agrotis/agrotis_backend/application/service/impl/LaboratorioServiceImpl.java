package com.agrotis.agrotis_backend.application.service.impl;

import com.agrotis.agrotis_backend.application.dto.FiltrarLaboratorioDTO;
import com.agrotis.agrotis_backend.application.dto.LaboratorioDTO;
import com.agrotis.agrotis_backend.application.dto.LaboratorioResponseDTO;
import com.agrotis.agrotis_backend.application.mapper.Mapper;
import com.agrotis.agrotis_backend.application.service.interfaces.LaboratorioService;
import com.agrotis.agrotis_backend.domain.model.Laboratorio;
import com.agrotis.agrotis_backend.repository.LaboratorioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioServiceImpl implements LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioServiceImpl(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    @Override
    public Optional<LaboratorioDTO> getLaboratorioById(Long id) {
        return laboratorioRepository.findById(id).map(Mapper::toDTO);
    }

    @Override
    public LaboratorioDTO addLaboratorio(LaboratorioDTO laboratorioDTO) {
        if (laboratorioDTO.getId() != null && laboratorioRepository.existsById(laboratorioDTO.getId())) {
            throw new IllegalArgumentException("Já existe um laboratório com o ID " + laboratorioDTO.getId());
        }
        Laboratorio laboratorio = Mapper.toEntity(laboratorioDTO);
        return Mapper.toDTO(laboratorioRepository.save(laboratorio));
    }

    @Override
    public List<LaboratorioResponseDTO> filtrarLaboratorios(FiltrarLaboratorioDTO filtros) {
        return laboratorioRepository.filtrarLaboratorios(
                filtros.getDataInicialComeco(),
                filtros.getDataInicialFim(),
                filtros.getDataFinalComeco(),
                filtros.getDataFinalFim(),
                filtros.getObservacoes(),
                filtros.getQuantidadePessoas());
    }

    @Override
    public void deleteLaboratorioById(Long id) {
        if (!laboratorioRepository.existsById(id)) {
            throw new EntityNotFoundException("Laboratório com ID " + id + " não encontrado");
        }
        laboratorioRepository.deleteById(id);
    }

    @Override
    public LaboratorioDTO atualizarLaboratorio(Long id, LaboratorioDTO laboratorioDTO) {

        Laboratorio laboratorio = laboratorioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Laboratório com ID " + id + " não encontrado"));

        laboratorio.setNome(laboratorioDTO.getNome());
        laboratorioRepository.save(laboratorio);
        return Mapper.toDTO(laboratorio);


    }
}
