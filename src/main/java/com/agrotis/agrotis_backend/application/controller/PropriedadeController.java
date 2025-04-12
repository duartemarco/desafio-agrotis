package com.agrotis.agrotis_backend.application.controller;

import com.agrotis.agrotis_backend.application.dto.PropriedadeDTO;
import com.agrotis.agrotis_backend.application.service.interfaces.PropriedadeService;
import jakarta.validation.Valid;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/propriedades")
public class PropriedadeController {

    private final PropriedadeService propriedadeService;

    public PropriedadeController(PropriedadeService propriedadeService) {
        this.propriedadeService = propriedadeService;
    }

    @Description("Cadastra uma nova propriedade")
    @PostMapping
    public ResponseEntity<PropriedadeDTO> cadastraPropriedade(@RequestBody PropriedadeDTO propriedadeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propriedadeService.addPropriedade(propriedadeDTO));
    }

    @Description("Consulta uma única Propriedade pelo seu ID")
    @GetMapping("/{id}")
    public ResponseEntity<PropriedadeDTO> consultaPropriedade(@PathVariable Long id) {
        return propriedadeService.getPropriedadeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Description("Atualiza as informações de uma Propriedade pelo seu ID")
    @PostMapping("/{id}")
    public ResponseEntity<PropriedadeDTO> atualizaPropriedade(@PathVariable Long id, @Valid @RequestBody PropriedadeDTO propriedadeDTO) {
        PropriedadeDTO atualizada = propriedadeService.atualizarPropriedade(id, propriedadeDTO);
        return ResponseEntity.ok(atualizada);
    }

    @Description("Deleta uma Propriedade pelo seu ID")
    @DeleteMapping("/{id}")
    public void apagarPropriedade(@PathVariable Long id) {
        propriedadeService.deletePropriedadeById(id);
    }


}
