package com.agrotis.agrotis_backend.service;

import com.agrotis.agrotis_backend.application.dto.LaboratorioDTO;
import com.agrotis.agrotis_backend.application.service.impl.LaboratorioServiceImpl;
import com.agrotis.agrotis_backend.domain.model.Laboratorio;
import com.agrotis.agrotis_backend.repository.LaboratorioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaboratorioServiceImplTest {

    private LaboratorioRepository laboratorioRepository;
    private LaboratorioServiceImpl laboratorioService;

    @BeforeEach
    void setUp() {
        laboratorioRepository = mock(LaboratorioRepository.class);
        laboratorioService = new LaboratorioServiceImpl(laboratorioRepository);
    }

    @Test
    void deveAdicionarNovoLaboratorio() {
        LaboratorioDTO dto = new LaboratorioDTO(null, "Laboratório João Pessoa");

        Laboratorio laboratorio = new Laboratorio(50L, "Laboratório João Pessoa", null);
        when(laboratorioRepository.save(any(Laboratorio.class))).thenReturn(laboratorio);

        LaboratorioDTO resultado = laboratorioService.addLaboratorio(dto);

        assertEquals("Laboratório João Pessoa", resultado.getNome());
        assertEquals(50L, resultado.getId());
    }

    @Test
    void naoDeveAdicionarLaboratorioComIdExistente() {
        LaboratorioDTO dto = new LaboratorioDTO(75L, "Laboratório Curitiba");

        when(laboratorioRepository.existsById(75L)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> laboratorioService.addLaboratorio(dto));
    }

    @Test
    void deveRetornarLaboratorioPorId() {
        Laboratorio laboratorio = new Laboratorio(25L, "Laboratório Teste", null);

        when(laboratorioRepository.findById(25L)).thenReturn(Optional.of(laboratorio));

        Optional<LaboratorioDTO> resultado = laboratorioService.getLaboratorioById(25L);

        assertTrue(resultado.isPresent());
        assertEquals("Laboratório Teste", resultado.get().getNome());
    }

    @Test
    void deveRetornarOptionalVazioParaLaboratorioInexistente() {
        when(laboratorioRepository.findById(50L)).thenReturn(Optional.empty());

        Optional<LaboratorioDTO> resultado = laboratorioService.getLaboratorioById(50L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void deveAtualizarLaboratorioExistente() {
        Laboratorio laboratorioExistente = new Laboratorio(50L, "Antigo Nome", null);
        LaboratorioDTO dto = new LaboratorioDTO(null, "Novo Nome");

        when(laboratorioRepository.findById(50L)).thenReturn(Optional.of(laboratorioExistente));
        when(laboratorioRepository.save(any(Laboratorio.class))).thenAnswer(invocation -> invocation.getArgument(0));

        LaboratorioDTO atualizado = laboratorioService.atualizarLaboratorio(50L, dto);

        assertEquals("Novo Nome", atualizado.getNome());
        assertEquals(50L, atualizado.getId());
    }

    @Test
    void deveLancarExcecaoAoAtualizarLaboratorioInexistente() {
        when(laboratorioRepository.findById(75L)).thenReturn(Optional.empty());

        LaboratorioDTO dto = new LaboratorioDTO(null, "Atualizar Nome");

        assertThrows(EntityNotFoundException.class, () -> laboratorioService.atualizarLaboratorio(75L, dto));
    }

    @Test
    void deveDeletarLaboratorioExistente() {
        when(laboratorioRepository.existsById(25L)).thenReturn(true);

        laboratorioService.deleteLaboratorioById(25L);

        verify(laboratorioRepository).deleteById(25L);
    }

    @Test
    void deveLancarExcecaoAoDeletarLaboratorioInexistente() {
        when(laboratorioRepository.existsById(50L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> laboratorioService.deleteLaboratorioById(50L));
    }

}