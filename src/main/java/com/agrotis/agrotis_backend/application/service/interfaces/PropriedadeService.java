package com.agrotis.agrotis_backend.application.service.interfaces;

import com.agrotis.agrotis_backend.application.dto.PropriedadeDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PropriedadeService {

    Optional<PropriedadeDTO> getPropriedadeById(Long id);

    PropriedadeDTO addPropriedade(PropriedadeDTO propriedadeDTO);

    void deletePropriedadeById(Long id);

    PropriedadeDTO atualizarPropriedade(Long id, PropriedadeDTO propriedadeDTO);


}
