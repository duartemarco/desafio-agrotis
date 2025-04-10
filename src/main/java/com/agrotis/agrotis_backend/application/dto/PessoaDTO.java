package com.agrotis.agrotis_backend.application.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;
    private String nome;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    private PropriedadeDTO infosPropriedade;
    private LaboratorioDTO laboratorio;
    private String observacoes;

}
