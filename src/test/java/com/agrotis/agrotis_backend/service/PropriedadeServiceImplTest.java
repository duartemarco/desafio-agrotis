package com.agrotis.agrotis_backend.service;

import com.agrotis.agrotis_backend.application.dto.PropriedadeDTO;
import com.agrotis.agrotis_backend.application.service.impl.PropriedadeServiceImpl;
import com.agrotis.agrotis_backend.domain.model.Propriedade;
import com.agrotis.agrotis_backend.repository.PropriedadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PropriedadeServiceImplTest {

    private PropriedadeRepository propriedadeRepository;
    private PropriedadeServiceImpl propriedadeService;

    @BeforeEach
    void setUp() {
        propriedadeRepository = mock(PropriedadeRepository.class);
        propriedadeService = new PropriedadeServiceImpl(propriedadeRepository);
    }

    @Test
    void deveAdicionarNovaPropriedade() {
        PropriedadeDTO dto = new PropriedadeDTO(null, "Fazenda João Pessoa");

        Propriedade propriedade = new Propriedade(50L, "Fazenda João Pessoa");
        when(propriedadeRepository.save(any(Propriedade.class))).thenReturn(propriedade);

        PropriedadeDTO resultado = propriedadeService.addPropriedade(dto);

        assertEquals("Fazenda João Pessoa", resultado.getNome());
        assertEquals(50L, resultado.getId());
    }

    @Test
    void naoDeveAdicionarPropriedadeComIdExistente() {
        PropriedadeDTO dto = new PropriedadeDTO(75L, "Fazenda Curitiba");

        when(propriedadeRepository.existsById(75L)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> propriedadeService.addPropriedade(dto));
    }

    @Test
    void deveRetornarPropriedadePorId() {
        Propriedade propriedade = new Propriedade(25L, "Fazenda Teste");

        when(propriedadeRepository.findById(25L)).thenReturn(Optional.of(propriedade));

        Optional<PropriedadeDTO> resultado = propriedadeService.getPropriedadeById(25L);

        assertTrue(resultado.isPresent());
        assertEquals("Fazenda Teste", resultado.get().getNome());
    }

    @Test
    void deveRetornarOptionalVazioParaPropriedadeInexistente() {
        when(propriedadeRepository.findById(50L)).thenReturn(Optional.empty());

        Optional<PropriedadeDTO> resultado = propriedadeService.getPropriedadeById(50L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void deveAtualizarPropriedadeExistente() {
        Propriedade propriedadeExistente = new Propriedade(50L, "Antigo Nome");
        PropriedadeDTO dto = new PropriedadeDTO(null, "Novo Nome");

        when(propriedadeRepository.findById(50L)).thenReturn(Optional.of(propriedadeExistente));
        when(propriedadeRepository.save(any(Propriedade.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PropriedadeDTO atualizado = propriedadeService.atualizarPropriedade(50L, dto);

        assertEquals("Novo Nome", atualizado.getNome());
        assertEquals(50L, atualizado.getId());
    }

    @Test
    void deveLancarExcecaoAoAtualizarPropriedadeInexistente() {
        when(propriedadeRepository.findById(75L)).thenReturn(Optional.empty());

        PropriedadeDTO dto = new PropriedadeDTO(null, "Atualizar Nome");

        assertThrows(EntityNotFoundException.class, () -> propriedadeService.atualizarPropriedade(75L, dto));
    }

    @Test
    void deveDeletarPropriedadeExistente() {
        when(propriedadeRepository.existsById(25L)).thenReturn(true);

        propriedadeService.deletePropriedadeById(25L);

        verify(propriedadeRepository).deleteById(25L);
    }

    @Test
    void deveLancarExcecaoAoDeletarPropriedadeInexistente() {
        when(propriedadeRepository.existsById(50L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> propriedadeService.deletePropriedadeById(50L));
    }
}