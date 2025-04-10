package com.agrotis.agrotis_backend.application.service.impl;

import com.agrotis.agrotis_backend.application.dto.FiltrarLaboratorioDTO;
import com.agrotis.agrotis_backend.application.dto.LaboratorioDTO;
import com.agrotis.agrotis_backend.application.mapper.Mapper;
import com.agrotis.agrotis_backend.application.service.interfaces.LaboratorioService;
import com.agrotis.agrotis_backend.repository.LaboratorioRepository;
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
        return laboratorioRepository.findById(id)
                .map(Mapper::toDTO);
    }

    @Override
    public LaboratorioDTO addLaboratorio(LaboratorioDTO laboratorioDTO) {
        return null;
    }

    @Override
    public List<LaboratorioDTO> filtrarLaboratorios(FiltrarLaboratorioDTO filtrarLaboratorioDTO) {
        return laboratorioRepository.filtrarLaboratorios(
                        filtrarLaboratorioDTO.getNome(),
                        filtrarLaboratorioDTO.getDataInicial(),
                        filtrarLaboratorioDTO.getDataFinal(),
                        filtrarLaboratorioDTO.getObservacoes(),
                        filtrarLaboratorioDTO.getQuantidadePessoas()
                );
    }

    @Override
    public void deleteLaboratorioById(Long id) {
        laboratorioRepository.deleteById(id);
    }

    @Override
    public Optional<LaboratorioDTO> atualizarLaboratorio(Long id, LaboratorioDTO laboratorioDTO) {
        return Optional.empty();
    }
}
