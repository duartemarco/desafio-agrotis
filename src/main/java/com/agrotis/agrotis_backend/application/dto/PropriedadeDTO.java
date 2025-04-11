package com.agrotis.agrotis_backend.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropriedadeDTO {

    @NotBlank
    private String nome;

}
