package com.agrotis.agrotis_backend.application.dto;

import com.agrotis.agrotis_backend.domain.model.Laboratorio;
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

    private PropriedadeDTO infosPropriedade;

    public PessoaDTO(Long id, String nome, LocalDateTime dataInicial, LocalDateTime dataFinal, PropriedadeDTO infosPropriedade, LaboratorioDTO laboratorio, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.infosPropriedade = infosPropriedade;
        this.laboratorio = laboratorio;
        this.observacoes = observacoes;
    }

    private LaboratorioDTO laboratorio;

    private String observacoes;


}
