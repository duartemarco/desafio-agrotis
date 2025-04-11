package com.agrotis.agrotis_backend.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "tbl_laboratorio")
@AllArgsConstructor
@NoArgsConstructor
public class Laboratorio {

    @Id
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "laboratorio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pessoa> pessoas;

}
