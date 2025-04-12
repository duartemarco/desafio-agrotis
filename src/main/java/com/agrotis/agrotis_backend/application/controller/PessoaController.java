package com.agrotis.agrotis_backend.application.controller;

import com.agrotis.agrotis_backend.application.dto.PessoaRequestDTO;
import com.agrotis.agrotis_backend.application.dto.PessoaDTO;
import com.agrotis.agrotis_backend.application.service.interfaces.PessoaService;
import jakarta.validation.Valid;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Description("Cadastra uma nova pessoa")
    @PostMapping
    public ResponseEntity<PessoaDTO> cadastraPessoa(@Valid @RequestBody PessoaRequestDTO pessoaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.addPessoa(pessoaDTO));
    }

    @Description("Consulta uma única Pessoa pelo seu ID")
    @GetMapping("/c{id}")
    public ResponseEntity<PessoaDTO> consultarPessoa(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.getPessoaById(id));
    }

    @Description("Lista todas as Pessoas")
    @GetMapping("/all")
    public ResponseEntity<List<PessoaDTO>> listarPessoas() {
        return ResponseEntity.ok(pessoaService.listarPessoas());
    }

    @Description("Atualiza as informações de uma Pessoa pelo seu ID")
    @PostMapping("/{id}")
    public ResponseEntity<PessoaDTO> atualizaPessoa(@PathVariable Long id, @Valid @RequestBody PessoaRequestDTO pessoaRequestDTO) {
        PessoaDTO atualizado = pessoaService.atualizarPessoa(id, pessoaRequestDTO);
        return ResponseEntity.ok(atualizado);
    }

    @Description("Deleta uma Pessoa pelo seu ID")
    @DeleteMapping("/{id}")
    public void apagarPessoa(@PathVariable Long id) {
        pessoaService.deletePessoaById(id);
    }

}
