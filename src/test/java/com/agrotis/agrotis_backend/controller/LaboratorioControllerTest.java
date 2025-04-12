package com.agrotis.agrotis_backend.controller;

import com.agrotis.agrotis_backend.application.controller.LaboratorioController;
import com.agrotis.agrotis_backend.application.dto.FiltrarLaboratorioDTO;
import com.agrotis.agrotis_backend.application.dto.LaboratorioDTO;
import com.agrotis.agrotis_backend.application.dto.LaboratorioResponseDTO;
import com.agrotis.agrotis_backend.application.service.interfaces.LaboratorioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaboratorioControllerTest {

    private LaboratorioService laboratorioService;
    private LaboratorioController controller;

    @BeforeEach
    void setUp() {
        laboratorioService = mock(LaboratorioService.class);
        controller = new LaboratorioController(laboratorioService);
    }

    @Test
    void deveCadastrarLaboratorioComSucesso() {
        LaboratorioDTO dtoEntrada = new LaboratorioDTO(null, "Laboratório RJ");
        LaboratorioDTO dtoSaida = new LaboratorioDTO(25L, "Laboratório RJ");

        when(laboratorioService.addLaboratorio(dtoEntrada)).thenReturn(dtoSaida);

        ResponseEntity<LaboratorioDTO> response = controller.cadastraLaboratorio(dtoEntrada);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(25L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Laboratório RJ", response.getBody().getNome());
    }

    @Test
    void deveConsultarLaboratorioExistente() {
        LaboratorioDTO dto = new LaboratorioDTO(50L, "Laboratório João Pessoa");

        when(laboratorioService.getLaboratorioById(50L)).thenReturn(Optional.of(dto));

        ResponseEntity<LaboratorioDTO> response = controller.consultaLaboratorio(50L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Laboratório João Pessoa", Objects.requireNonNull(response.getBody()).getNome());
    }

    @Test
    void deveRetornarNotFoundAoConsultarLaboratorioInexistente() {
        when(laboratorioService.getLaboratorioById(75L)).thenReturn(Optional.empty());

        ResponseEntity<LaboratorioDTO> response = controller.consultaLaboratorio(75L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deveFiltrarLaboratorios() {
        FiltrarLaboratorioDTO filtros = new FiltrarLaboratorioDTO();
        List<LaboratorioResponseDTO> lista = Arrays.asList(
                new LaboratorioResponseDTO(1L, "Lab A", 3L),
                new LaboratorioResponseDTO(2L, "Lab B", 4L)
        );

        when(laboratorioService.filtrarLaboratorios(filtros)).thenReturn(lista);

        ResponseEntity<List<LaboratorioResponseDTO>> response = controller.filtrarLaboratorios(filtros);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void deveAtualizarLaboratorio() {
        LaboratorioDTO entrada = new LaboratorioDTO(null, "Nome Atualizado");
        LaboratorioDTO atualizado = new LaboratorioDTO(25L, "Nome Atualizado");

        when(laboratorioService.atualizarLaboratorio(25L, entrada)).thenReturn(atualizado);

        ResponseEntity<LaboratorioDTO> response = controller.atualizaLaboratorio(25L, entrada);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Nome Atualizado", Objects.requireNonNull(response.getBody()).getNome());
    }

    @Test
    void deveChamarDeleteLaboratorio() {
        doNothing().when(laboratorioService).deleteLaboratorioById(10L);

        controller.apagarLaboratorio(10L);

        verify(laboratorioService).deleteLaboratorioById(10L);
    }
}
