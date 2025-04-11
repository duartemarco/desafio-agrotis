package com.agrotis.agrotis_backend.controller;

import com.agrotis.agrotis_backend.application.dto.LaboratorioDTO;
import com.agrotis.agrotis_backend.application.service.impl.LaboratorioServiceImpl;
import com.agrotis.agrotis_backend.domain.model.Laboratorio;
import com.agrotis.agrotis_backend.repository.LaboratorioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaboratorioServiceImplTest {

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @InjectMocks
    private LaboratorioServiceImpl laboratorioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarLaboratorioPorId() {
        Laboratorio laboratorio = new Laboratorio(1L, "Lab Teste", null);

        when(laboratorioRepository.findById(1L)).thenReturn(Optional.of(laboratorio));

        Optional<LaboratorioDTO> result = laboratorioService.getLaboratorioById(1L);

        assertTrue(result.isPresent());
        assertEquals("Lab Teste", result.get().getNome());
    }

    @Test
    void deveSalvarLaboratorioQuandoIdNaoExiste() {
        LaboratorioDTO dto = new LaboratorioDTO(null, "Novo Lab");

        Laboratorio laboratorio = new Laboratorio(null, "Novo Lab", null);

        when(laboratorioRepository.save(any(Laboratorio.class)))
                .thenReturn(new Laboratorio(1L, "Novo Lab", null));

        LaboratorioDTO result = laboratorioService.addLaboratorio(dto);

        assertNotNull(result);
        assertEquals("Novo Lab", result.getNome());
    }

    @Test
    void naoDeveSalvarLaboratorioComIdExistente() {
        LaboratorioDTO dto = new LaboratorioDTO(1L, "Lab Existente");

        when(laboratorioRepository.existsById(1L)).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> laboratorioService.addLaboratorio(dto));

        assertEquals("Já existe um Laboratório com o ID 1", ex.getMessage());
    }

    @Test
    void deveAtualizarLaboratorio() {

        Laboratorio laboratorio = new Laboratorio(1L, "Antigo Nome", null);

        when(laboratorioRepository.findById(1L)).thenReturn(Optional.of(laboratorio));
        when(laboratorioRepository.save(any())).thenReturn(laboratorio);

        LaboratorioDTO dto = new LaboratorioDTO(null, "Novo Nome");
        LaboratorioDTO result = laboratorioService.atualizarLaboratorio(1L, dto);

        assertEquals("Novo Nome", result.getNome());
    }

    @Test
    void deveLancarErroAoAtualizarLaboratorioInexistente() {

        when(laboratorioRepository.findById(25L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> laboratorioService.atualizarLaboratorio(25L, new LaboratorioDTO()));

        assertEquals("Laboratório com ID 25 não encontrado", ex.getMessage());
    }

    @Test
    void deveDeletarLaboratorio() {

        when(laboratorioRepository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> laboratorioService.deleteLaboratorioById(1L));
        verify(laboratorioRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarErroAoDeletarLaboratorioInexistente() {

        when(laboratorioRepository.existsById(48L)).thenReturn(false);

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> laboratorioService.deleteLaboratorioById(48L));

        assertEquals("Laboratório com ID 48 não encontrado", ex.getMessage());
    }

}