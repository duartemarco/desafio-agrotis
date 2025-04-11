package com.agrotis.agrotis_backend.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboratorioDTO {

    private Long id;

    @NotBlank
    private String nome;

}
