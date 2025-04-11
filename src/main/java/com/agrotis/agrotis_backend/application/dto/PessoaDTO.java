package com.agrotis.agrotis_backend.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private LocalDateTime dataInicial;

    @NotNull
    private LocalDateTime dataFinal;

    private PropriedadeDTO infosPropriedade;
    private LaboratorioDTO laboratorio;

    private String observacoes;

}
