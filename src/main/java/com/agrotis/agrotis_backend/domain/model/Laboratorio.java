package com.agrotis.agrotis_backend.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "tbl_laboratorio")
@AllArgsConstructor
@NoArgsConstructor
public class Laboratorio {

    @Id
    private Long id;
    private String nome;

}
