package com.agrotis.agrotis_backend.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "tbl_laboratorio")
public class Laboratorio {

    @Id
    private Long id;
    private String nome;

}
