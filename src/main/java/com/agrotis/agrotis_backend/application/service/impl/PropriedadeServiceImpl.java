package com.agrotis.agrotis_backend.application.service.impl;

import com.agrotis.agrotis_backend.application.dto.PropriedadeDTO;
import com.agrotis.agrotis_backend.application.mapper.Mapper;
import com.agrotis.agrotis_backend.application.service.interfaces.PropriedadeService;
import com.agrotis.agrotis_backend.domain.model.Propriedade;
import com.agrotis.agrotis_backend.repository.PropriedadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropriedadeServiceImpl implements PropriedadeService {

    private final PropriedadeRepository propriedadeRepository;

    public PropriedadeServiceImpl(PropriedadeRepository propriedadeRepository ) {
        this.propriedadeRepository = propriedadeRepository;
    }


    @Override
    public Optional<PropriedadeDTO> getPropriedadeById(Long id) {
        return propriedadeRepository.findById(id).map(Mapper::toDTO);
    }

    @Override
    public PropriedadeDTO addPropriedade(PropriedadeDTO propriedadeDTO) {
        if (propriedadeDTO.getId() != null && propriedadeRepository.existsById(propriedadeDTO.getId())) {
            throw new IllegalArgumentException("Já existe uma propriedade com o ID " + propriedadeDTO.getId());
        }
        Propriedade propriedade = Mapper.toEntity(propriedadeDTO);
        return Mapper.toDTO(propriedadeRepository.save(propriedade));
    }

    @Override
    public void deletePropriedadeById(Long id) {
        if (!propriedadeRepository.existsById(id)) {
            throw new EntityNotFoundException("Propriedade com ID " + id + " não encontrada");
        }
        propriedadeRepository.deleteById(id);
    }

    @Override
    public PropriedadeDTO atualizarPropriedade(Long id, PropriedadeDTO propriedadeDTO) {

        Propriedade propriedade = propriedadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Propriedade com ID " + id + " não encontrada"));

        propriedade.setNome(propriedadeDTO.getNome());
        propriedadeRepository.save(propriedade);
        return Mapper.toDTO(propriedade);
    }

}
