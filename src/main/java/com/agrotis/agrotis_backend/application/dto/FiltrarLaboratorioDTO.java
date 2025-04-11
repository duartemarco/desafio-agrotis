package com.agrotis.agrotis_backend.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltrarLaboratorioDTO {

    private LocalDateTime dataInicialComeco;
    private LocalDateTime dataInicialFim;
    private LocalDateTime dataFinalComeco;
    private LocalDateTime dataFinalFim;
    private String observacoes;
    @NotNull
    private Integer quantidadePessoas;


}
