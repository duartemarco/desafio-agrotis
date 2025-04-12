package com.agrotis.agrotis_backend.application.controller;

import com.agrotis.agrotis_backend.application.dto.FiltrarLaboratorioDTO;
import com.agrotis.agrotis_backend.application.dto.LaboratorioDTO;
import com.agrotis.agrotis_backend.application.dto.LaboratorioResponseDTO;
import com.agrotis.agrotis_backend.application.service.interfaces.LaboratorioService;
import jakarta.validation.Valid;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laboratorios")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @Description("Cadastra um novo laboratório")
    @PostMapping
    public ResponseEntity<LaboratorioDTO> cadastraLaboratorio(@RequestBody LaboratorioDTO laboratorioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(laboratorioService.addLaboratorio(laboratorioDTO));
    }

    @Description("Consulta um único Laboratório pelo seu ID")
    @GetMapping
    public ResponseEntity<LaboratorioDTO> consultaLaboratorio(@PathVariable Long id) {
        return laboratorioService.getLaboratorioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Description("Lista todos os Laboratórios com base nos filtros utilizados")
    @PostMapping("/all")
    public ResponseEntity<List<LaboratorioResponseDTO>> filtrarLaboratorios(@Valid @RequestBody FiltrarLaboratorioDTO filtros) {
        List<LaboratorioResponseDTO> filtrados = laboratorioService.filtrarLaboratorios(filtros);
        return ResponseEntity.ok(filtrados);
    }

    @Description("Atualiza as informações de um Laboratório pelo seu ID")
    @PostMapping("/{id}")
    public ResponseEntity<LaboratorioDTO> atualizaLaboratorio(@PathVariable Long id, @Valid @RequestBody LaboratorioDTO laboratorioDTO) {
        LaboratorioDTO atualizado = laboratorioService.atualizarLaboratorio(id, laboratorioDTO);
        return ResponseEntity.ok(atualizado);
    }

    @Description("Deleta um Laboratório pelo seu ID")
    @DeleteMapping("/{id}")
    public void apagarLaboratorio(@PathVariable Long id) {
        laboratorioService.deleteLaboratorioById(id);
    }


}
