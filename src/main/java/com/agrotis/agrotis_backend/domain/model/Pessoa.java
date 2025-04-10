package com.agrotis.agrotis_backend.domain.model;

import jakarta.persistence.*;
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

    private String nome;

    private LocalDateTime dataInicial;

    private LocalDateTime dataFinal;

    @ManyToOne
    @JoinColumn(name = "infos_propriedade_id")
    private Propriedade infosPropriedade;

    @ManyToOne
    @JoinColumn(name = "laboratorio_id")
    private Laboratorio laboratorio;

    private String observacoes;

}
