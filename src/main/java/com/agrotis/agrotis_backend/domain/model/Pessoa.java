package com.agrotis.agrotis_backend.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Propriedade infosPropriedade;

    private Laboratorio laboratorio;

    private String observacoes;

}
