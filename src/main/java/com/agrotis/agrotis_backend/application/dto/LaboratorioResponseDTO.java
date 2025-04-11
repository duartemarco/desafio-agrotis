package com.agrotis.agrotis_backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboratorioResponseDTO {

    private Long id;
    private String nome;
    private Long quantidadePessoas;

}
