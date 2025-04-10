package com.agrotis.agrotis_backend.application.controller;

import com.agrotis.agrotis_backend.application.dto.AddPessoaDTO;
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
    @PostMapping("cadastrar")
    public ResponseEntity<PessoaDTO> cadastraPessoa(@Valid @RequestBody AddPessoaDTO pessoaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.addPessoa(pessoaDTO));
    }

    @Description("Consulta uma única Pessoa pelo seu ID")
    @GetMapping("/consultar/{id}")
    public ResponseEntity<PessoaDTO> consultarPessoa(@PathVariable Long id) {
        return pessoaService.getPessoaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Description("Lista todas as Pessoas")
    @GetMapping("/consultar/all")
    public ResponseEntity<List<PessoaDTO>> listarPessoas() {
        return ResponseEntity.ok(pessoaService.listarPessoas());
    }

    @Description("Atualiza as informações de uma Pessoa pelo seu ID")
    @PostMapping("/atualizar/{id}")
    public ResponseEntity<PessoaDTO> atualizaPessoa(@PathVariable Long id, @Valid @RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO atualizado = pessoaService.atualizarPessoa(id, pessoaDTO);
        return ResponseEntity.ok(atualizado);
    }

    @Description("Deleta uma Pessoa pelo seu ID")
    @DeleteMapping("/apagar/{id}")
    public void apagarPessoa(@PathVariable Long id) {
        pessoaService.deletePessoaById(id);
    }


}
