package com.agrotis.agrotis_backend.application.dto;

import com.agrotis.agrotis_backend.domain.model.Pessoa;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;
    private String nome;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;

    private Long propriedadeId;
    private String propriedadeNome;

    private Long laboratorioId;
    private String laboratorioNome;

    private String observacoes;


}
