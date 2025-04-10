package com.agrotis.agrotis_backend.application.controller;

import com.agrotis.agrotis_backend.application.service.interfaces.PessoaService;
import com.agrotis.agrotis_backend.domain.model.Pessoa;
import com.agrotis.agrotis_backend.application.dto.PessoaDTO;
import com.agrotis.agrotis_backend.repository.PessoaRepository;
import jakarta.validation.Valid;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.agrotis.agrotis_backend.application.mapper.PessoaMapper.toDTO;
import static com.agrotis.agrotis_backend.application.mapper.PessoaMapper.toEntity;

@RestController
@RequestMapping("/clientes")
public class PessoaController {

    private final PessoaService pessoaService;
    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaService pessoaService, PessoaRepository pessoaRepository) {
        this.pessoaService = pessoaService;
        this.pessoaRepository = pessoaRepository;
    }

    @Description("Cadastra uma nova pessoa")
    @PostMapping
    public ResponseEntity<PessoaDTO> cadastraPessoa(@Valid @RequestBody PessoaDTO dto) {
        Pessoa pessoa = toEntity(dto);
        pessoa = pessoaRepository.save(pessoa);
        PessoaDTO response = toDTO(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Description("Consulta uma única pessoa pelo seu ID")
    @GetMapping("/consultar/{id}")
    public Optional<Pessoa> consultarPessoa(@PathVariable Long id) {
        return pessoaService.getPessoaById(id);
    }


}
