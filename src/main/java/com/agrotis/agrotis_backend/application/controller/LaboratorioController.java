package com.agrotis.agrotis_backend.application.controller;

import com.agrotis.agrotis_backend.application.dto.LaboratorioDTO;
import com.agrotis.agrotis_backend.application.dto.PessoaDTO;
import com.agrotis.agrotis_backend.application.service.interfaces.LaboratorioService;
import jakarta.validation.Valid;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/laboratorios")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @Description("Cadastra um novo laboratório")
    @PostMapping("cadastrar")
    public ResponseEntity<LaboratorioDTO> cadastraLaboratorio(@RequestBody LaboratorioDTO laboratorioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(laboratorioService.addLaboratorio(laboratorioDTO));
    }

    @Description("Consulta um único Laboratório pelo seu ID")
    @GetMapping("/consultar/{id}")
    public ResponseEntity<LaboratorioDTO> consultaLaboratorio(@PathVariable Long id) {
        return laboratorioService.getLaboratorioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Description("Atualiza as informações de um Laboratório pelo seu ID")
    @PostMapping("/atualizar/{id}")
    public ResponseEntity<LaboratorioDTO> atualizaLaboratorio(@PathVariable Long id, @Valid @RequestBody LaboratorioDTO laboratorioDTO) {
        return laboratorioService.atualizarLaboratorio(id, laboratorioDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Description("Deleta um Laboratório pelo seu ID")
    @DeleteMapping("/apagar/{id}")
    public void apagarLaboratorio(@PathVariable Long id) {
        laboratorioService.deleteLaboratorioById(id);
    }


}
