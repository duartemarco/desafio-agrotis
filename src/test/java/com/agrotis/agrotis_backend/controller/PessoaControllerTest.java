package com.agrotis.agrotis_backend.controller;

import com.agrotis.agrotis_backend.application.controller.PessoaController;
import com.agrotis.agrotis_backend.application.dto.LaboratorioDTO;
import com.agrotis.agrotis_backend.application.dto.PessoaDTO;
import com.agrotis.agrotis_backend.application.dto.PessoaRequestDTO;
import com.agrotis.agrotis_backend.application.dto.PropriedadeDTO;
import com.agrotis.agrotis_backend.application.service.interfaces.PessoaService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaControllerTest {

    private PessoaService pessoaService;
    private PessoaController controller;

    @BeforeEach
    void setUp() {
        pessoaService = mock(PessoaService.class);
        controller = new PessoaController(pessoaService);
    }

    @Test
    void deveCadastrarPessoaComSucesso() {
        PessoaRequestDTO dto = new PessoaRequestDTO(
                "João",
                LocalDateTime.of(2025, 4, 11, 10, 30),
                LocalDateTime.of(2025, 4, 11, 12, 30),
                50L,
                25L,
                "Observações"
        );

        PropriedadeDTO propriedadeDTO = new PropriedadeDTO(50L, "Fazenda João Pessoa");
        LaboratorioDTO laboratorioDTO = new LaboratorioDTO(25L, "Laboratório A");

        PessoaDTO esperado = new PessoaDTO(
                null,
                "João",
                dto.getDataInicial(),
                dto.getDataFinal(),
                propriedadeDTO,
                laboratorioDTO,
                dto.getObservacoes()
        );

        when(pessoaService.addPessoa(dto)).thenReturn(esperado);

        ResponseEntity<PessoaDTO> resposta = controller.cadastraPessoa(dto);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals("João", resposta.getBody().getNome());
        assertEquals(LocalDateTime.of(2025, 4, 11, 10, 30), resposta.getBody().getDataInicial());
        assertEquals(50L, resposta.getBody().getInfosPropriedade().getId());
        assertEquals(25L, resposta.getBody().getLaboratorio().getId());
        assertEquals("Observações", resposta.getBody().getObservacoes());
    }

    @Test
    void deveConsultarPessoaPorId() {
        PessoaDTO pessoa = new PessoaDTO(
                25L,
                "Ana",
                LocalDateTime.of(2025, 4, 11, 10, 30),
                LocalDateTime.of(2025, 4, 11, 12, 30),
                null,
                null,
                "Observações"
        );

        when(pessoaService.getPessoaById(25L)).thenReturn(pessoa);

        ResponseEntity<PessoaDTO> response = controller.consultarPessoa(25L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Ana", response.getBody().getNome());
    }

    @Test
    void deveListarTodasAsPessoas() {
        List<PessoaDTO> lista = List.of(
                new PessoaDTO(25L, "Pessoa 1",
                        LocalDateTime.of(2025, 4, 11, 10, 30),
                        LocalDateTime.of(2025, 4, 11, 12, 30),
                        null, null, "Observações"),
                new PessoaDTO(50L, "Pessoa 2",
                        LocalDateTime.of(2025, 4, 11, 10, 30),
                        LocalDateTime.of(2025, 4, 11, 12, 30),
                        null, null, "Observações")
        );

        when(pessoaService.listarPessoas()).thenReturn(lista);

        ResponseEntity<List<PessoaDTO>> response = controller.listarPessoas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Pessoa 1", response.getBody().get(0).getNome());
    }

    @Test
    void deveAtualizarPessoaComSucesso() {
        PessoaRequestDTO dto = new PessoaRequestDTO(
                "João Atualizado",
                LocalDateTime.of(2025, 4, 11, 9, 30),
                LocalDateTime.of(2025, 4, 11, 11, 30),
                50L,
                25L,
                "Novas Observações"
        );

        PropriedadeDTO propriedadeDTO = new PropriedadeDTO(50L, "Fazenda João Pessoa");
        LaboratorioDTO laboratorioDTO = new LaboratorioDTO(25L, "Laboratório A");

        PessoaDTO atualizado = new PessoaDTO(
                50L,
                "João Atualizado",
                dto.getDataInicial(),
                dto.getDataFinal(),
                propriedadeDTO,
                laboratorioDTO,
                dto.getObservacoes()
        );

        when(pessoaService.atualizarPessoa(50L, dto)).thenReturn(atualizado);

        ResponseEntity<PessoaDTO> response = controller.atualizaPessoa(50L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("João Atualizado", response.getBody().getNome());
        assertEquals("Novas Observações", response.getBody().getObservacoes());
    }

    @Test
    void deveChamarDeletePessoa() {
        doNothing().when(pessoaService).deletePessoaById(25L);

        controller.apagarPessoa(25L);

        verify(pessoaService).deletePessoaById(25L);
    }

    @Test
    void deveLancarExcecaoQuandoPessoaNaoExiste() {
        when(pessoaService.getPessoaById(99L)).thenThrow(new EntityNotFoundException("Pessoa não encontrada"));

        assertThrows(EntityNotFoundException.class, () -> controller.consultarPessoa(99L));
    }
}
