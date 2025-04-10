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

    private Long id;
    private String nome;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;

    @NotNull
    private Integer quantidadePessoas;

    private String observacoes;

}
