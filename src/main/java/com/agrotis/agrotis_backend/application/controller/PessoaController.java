package com.agrotis.agrotis_backend.application.controller;

import com.agrotis.agrotis_backend.application.service.interfaces.PessoaService;
import com.agrotis.agrotis_backend.domain.model.Pessoa;
import com.agrotis.agrotis_backend.application.dto.PessoaDTO;
import jakarta.validation.Valid;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.agrotis.agrotis_backend.application.mapper.PessoaMapper.toDTO;
import static com.agrotis.agrotis_backend.application.mapper.PessoaMapper.toEntity;

@RestController
@RequestMapping("/clientes")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Description("Cadastra uma nova pessoa")
    @PostMapping
    public ResponseEntity<PessoaDTO> cadastraPessoa(@Valid @RequestBody PessoaDTO dto) {
        Pessoa pessoa = toEntity(dto);
        pessoa = pessoaService.addPessoa(pessoa);
        PessoaDTO response = toDTO(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Description("Consulta uma única pessoa pelo seu ID")
    @GetMapping("/consultar/{id}")
    public ResponseEntity<PessoaDTO> consultarPessoa(@PathVariable Long id) {
        return pessoaService.getPessoaById(id).map(pessoa -> ResponseEntity.ok(toDTO(pessoa)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
