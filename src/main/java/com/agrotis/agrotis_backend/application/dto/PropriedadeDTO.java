package com.agrotis.agrotis_backend.application.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropriedadeDTO {

    private Long id;
    private String nome;

}
