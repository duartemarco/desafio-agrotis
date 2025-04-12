package com.agrotis.agrotis_backend.controller;

import com.agrotis.agrotis_backend.application.controller.PropriedadeController;
import com.agrotis.agrotis_backend.application.dto.PropriedadeDTO;
import com.agrotis.agrotis_backend.application.service.interfaces.PropriedadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PropriedadeControllerTest {

    private PropriedadeService propriedadeService;
    private PropriedadeController controller;

    @BeforeEach
    void setUp() {
        propriedadeService = mock(PropriedadeService.class);
        controller = new PropriedadeController(propriedadeService);
    }

    @Test
    void deveCadastrarPropriedadeComSucesso() {
        PropriedadeDTO entrada = new PropriedadeDTO(null, "Fazenda João Pessoa");
        PropriedadeDTO saida = new PropriedadeDTO(50L, "Fazenda João Pessoa");

        when(propriedadeService.addPropriedade(entrada)).thenReturn(saida);

        ResponseEntity<PropriedadeDTO> response = controller.cadastraPropriedade(entrada);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(50L, Objects.requireNonNull(response.getBody()).getId());
        assertEquals("Fazenda João Pessoa", response.getBody().getNome());
    }

    @Test
    void deveConsultarPropriedadeExistente() {
        PropriedadeDTO dto = new PropriedadeDTO(25L, "Fazenda Brasília");

        when(propriedadeService.getPropriedadeById(25L)).thenReturn(Optional.of(dto));

        ResponseEntity<PropriedadeDTO> response = controller.consultaPropriedade(25L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Fazenda Brasília", Objects.requireNonNull(response.getBody()).getNome());
    }

    @Test
    void deveRetornarNotFoundAoConsultarPropriedadeInexistente() {
        when(propriedadeService.getPropriedadeById(99L)).thenReturn(Optional.empty());

        ResponseEntity<PropriedadeDTO> response = controller.consultaPropriedade(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deveAtualizarPropriedadeComSucesso() {
        PropriedadeDTO entrada = new PropriedadeDTO(null, "Nome Atualizado");
        PropriedadeDTO atualizada = new PropriedadeDTO(25L, "Nome Atualizado");

        when(propriedadeService.atualizarPropriedade(25L, entrada)).thenReturn(atualizada);

        ResponseEntity<PropriedadeDTO> response = controller.atualizaPropriedade(25L, entrada);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Nome Atualizado", Objects.requireNonNull(response.getBody()).getNome());
    }

    @Test
    void deveChamarDeletePropriedade() {
        doNothing().when(propriedadeService).deletePropriedadeById(10L);

        controller.apagarPropriedade(10L);

        verify(propriedadeService).deletePropriedadeById(10L);
    }
}
