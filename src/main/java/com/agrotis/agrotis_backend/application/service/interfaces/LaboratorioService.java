package com.agrotis.agrotis_backend.application.service.interfaces;

import com.agrotis.agrotis_backend.application.dto.FiltrarLaboratorioDTO;
import com.agrotis.agrotis_backend.application.dto.LaboratorioDTO;
import com.agrotis.agrotis_backend.application.dto.LaboratorioResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface LaboratorioService {

    Optional<LaboratorioDTO> getLaboratorioById(Long id);

    LaboratorioDTO addLaboratorio(LaboratorioDTO laboratorioDTO);

    void deleteLaboratorioById(Long id);

    LaboratorioDTO atualizarLaboratorio(Long id, LaboratorioDTO laboratorioDTO);

    List<LaboratorioResponseDTO> filtrarLaboratorios(FiltrarLaboratorioDTO filtrarLaboratorioDTO);
}
