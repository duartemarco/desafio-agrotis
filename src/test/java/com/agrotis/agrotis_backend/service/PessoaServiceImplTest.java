package com.agrotis.agrotis_backend.application.service.impl;

import com.agrotis.agrotis_backend.application.dto.PessoaRequestDTO;
import com.agrotis.agrotis_backend.application.dto.PessoaDTO;
import com.agrotis.agrotis_backend.domain.model.Laboratorio;
import com.agrotis.agrotis_backend.domain.model.Pessoa;
import com.agrotis.agrotis_backend.domain.model.Propriedade;
import com.agrotis.agrotis_backend.repository.LaboratorioRepository;
import com.agrotis.agrotis_backend.repository.PessoaRepository;
import com.agrotis.agrotis_backend.repository.PropriedadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaServiceImplTest {

    private PessoaRepository pessoaRepository;
    private LaboratorioRepository laboratorioRepository;
    private PropriedadeRepository propriedadeRepository;
    private PessoaServiceImpl pessoaService;

    @BeforeEach
    void setUp() {
        pessoaRepository = mock(PessoaRepository.class);
        laboratorioRepository = mock(LaboratorioRepository.class);
        propriedadeRepository = mock(PropriedadeRepository.class);
        pessoaService = new PessoaServiceImpl(pessoaRepository, laboratorioRepository, propriedadeRepository);
    }

    @Test
    void deveAdicionarNovaPessoa() {
        PessoaRequestDTO dto = new PessoaRequestDTO("João", LocalDateTime.of(2025, 4, 11, 10, 30), LocalDateTime.of(2025, 4, 11, 12, 30), 50L, 25L, "Observações");

        Propriedade propriedade = new Propriedade(50L, "Fazenda João Pessoa");
        Laboratorio laboratorio = new Laboratorio(25L, "Laboratório A", new ArrayList<>());

        when(propriedadeRepository.findById(50L)).thenReturn(Optional.of(propriedade));
        when(laboratorioRepository.findById(25L)).thenReturn(Optional.of(laboratorio));

        Pessoa pessoa = new Pessoa(null, "João", LocalDateTime.of(2025, 4, 11, 10, 30), LocalDateTime.of(2025, 4, 11, 12, 30), propriedade, laboratorio, "Observações");
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaDTO resultado = pessoaService.addPessoa(dto);

        assertEquals("João", resultado.getNome());
        assertEquals(LocalDateTime.of(2025, 4, 11, 10, 30), resultado.getDataInicial());
        assertEquals(null, resultado.getId());
    }


    @Test
    void naoDeveAdicionarPessoaComPropriedadeOuLaboratorioInexistentes() {
        PessoaRequestDTO dto = new PessoaRequestDTO("Carlos", LocalDateTime.of(2025, 4, 11, 10, 30), LocalDateTime.of(2025, 4, 11, 12, 30), 50L, 25L, "Observações");

        when(propriedadeRepository.findById(50L)).thenReturn(Optional.empty());
        Laboratorio laboratorio = new Laboratorio(25L, "Laboratório A", new ArrayList<>());

        assertThrows(EntityNotFoundException.class, () -> pessoaService.addPessoa(dto));
    }

    @Test
    void deveRetornarPessoaPorId() {
        Pessoa pessoa = new Pessoa(25L, "Ana", LocalDateTime.of(2025, 4, 11, 10, 30), LocalDateTime.of(2025, 4, 11, 12, 30), null, null, "Observações");

        when(pessoaRepository.findById(25L)).thenReturn(Optional.of(pessoa));

        PessoaDTO resultado = pessoaService.getPessoaById(25L);

        assertEquals("Ana", resultado.getNome());
        assertEquals(LocalDateTime.of(2025, 4, 11, 10, 30), resultado.getDataInicial());
    }

    @Test
    void deveLancarExcecaoSePessoaNaoExistirAoBuscarPorId() {
        when(pessoaRepository.findById(50L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> pessoaService.getPessoaById(50L));
    }

    @Test
    void deveAtualizarPessoaExistente() {
        Pessoa pessoaExistente = new Pessoa(null, "Maria", LocalDateTime.of(2025, 4, 11, 9, 30), LocalDateTime.of(2025, 4, 11, 10, 30), null, null, "Observações");

        PessoaRequestDTO dto = new PessoaRequestDTO("Maria Silva", LocalDateTime.of(2025, 4, 11, 9, 30), LocalDateTime.of(2025, 4, 11, 11, 30), 50L, 25L, "Novas Observações");

        Propriedade propriedade = new Propriedade(50L, "Fazenda João Pessoa");
        Laboratorio laboratorio = new Laboratorio(25L, "Laboratório A", new ArrayList<>());

        when(pessoaRepository.findById(50L)).thenReturn(Optional.of(pessoaExistente));
        when(propriedadeRepository.findById(50L)).thenReturn(Optional.of(propriedade));
        when(laboratorioRepository.findById(25L)).thenReturn(Optional.of(laboratorio));

        when(pessoaRepository.save(any(Pessoa.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PessoaDTO atualizado = pessoaService.atualizarPessoa(50L, dto);

        assertEquals("Maria Silva", atualizado.getNome());
        assertEquals(LocalDateTime.of(2025, 4, 11, 9, 30), atualizado.getDataInicial());
        assertEquals("Novas Observações", atualizado.getObservacoes());
    }


    @Test
    void deveLancarExcecaoAoAtualizarPessoaInexistente() {
        when(pessoaRepository.findById(75L)).thenReturn(Optional.empty());

        PessoaRequestDTO dto = new PessoaRequestDTO("João Silva", LocalDateTime.of(2025, 4, 11, 8, 30), LocalDateTime.of(2025, 4, 11, 10, 30), 50L, 25L, "Observações");

        assertThrows(EntityNotFoundException.class, () -> pessoaService.atualizarPessoa(75L, dto));
    }

    @Test
    void deveDeletarPessoaExistente() {
        when(pessoaRepository.existsById(25L)).thenReturn(true);

        pessoaService.deletePessoaById(25L);

        verify(pessoaRepository).deleteById(25L);
    }

    @Test
    void deveLancarExcecaoAoDeletarPessoaInexistente() {
        when(pessoaRepository.existsById(50L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> pessoaService.deletePessoaById(50L));
    }

    @Test
    void deveListarTodasAsPessoas() {
        Pessoa pessoa1 = new Pessoa(25L, "Pessoa 1", LocalDateTime.of(2025, 4, 11, 10, 30), LocalDateTime.of(2025, 4, 11, 12, 30), null, null, "Observações");
        Pessoa pessoa2 = new Pessoa(50L, "Pessoa 2", LocalDateTime.of(2025, 4, 11, 10, 30), LocalDateTime.of(2025, 4, 11, 12, 30), null, null, "Observações");

        when(pessoaRepository.findAll()).thenReturn(List.of(pessoa1, pessoa2));

        var resultado = pessoaService.listarPessoas();

        assertEquals(2, resultado.size());
        assertEquals("Pessoa 1", resultado.get(0).getNome());
        assertEquals("Pessoa 2", resultado.get(1).getNome());
    }
}
