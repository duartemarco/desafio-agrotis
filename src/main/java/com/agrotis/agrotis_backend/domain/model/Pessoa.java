package com.agrotis.agrotis_backend.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbl_pessoa")
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Column(name = "data_inicial")
    private LocalDateTime dataInicial;

    @NotNull
    @Column(name = "data_final")
    private LocalDateTime dataFinal;

    @ManyToOne
    @JoinColumn(name = "infos_propriedade_id")
    private Propriedade infosPropriedade;

    @ManyToOne
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;

    private String observacoes;

}
