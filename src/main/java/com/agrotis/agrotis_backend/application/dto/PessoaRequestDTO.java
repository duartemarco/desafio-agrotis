package com.agrotis.agrotis_backend.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    private LocalDateTime dataInicial;

    @NotNull
    private LocalDateTime dataFinal;

    private Long idPropriedade;
    private Long idLaboratorio;

    private String observacoes;

}
