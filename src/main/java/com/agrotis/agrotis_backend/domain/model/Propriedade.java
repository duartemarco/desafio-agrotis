package com.agrotis.agrotis_backend.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_propriedade")
public class Propriedade {

    @Id
    private Long id;
    private String nome;

}
