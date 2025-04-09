package com.agrotis.agrotis_backend.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbl_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDateTime dataInicial;

    private LocalDateTime dataFinal;

    private Propriedade infosPropriedade;

    private Laboratorio laboratorio;

    private String observacoes;

}
