package com.agrotis.agrotis_backend.application.mapper;

import com.agrotis.agrotis_backend.application.dto.ClienteDTO;
import com.agrotis.agrotis_backend.domain.model.Cliente;
import com.agrotis.agrotis_backend.domain.model.Laboratorio;
import com.agrotis.agrotis_backend.domain.model.Propriedade;

public class ClienteMapper {

    public static ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getDataFinal(),
                cliente.getDataInicial(),
                cliente.getInfosPropriedade().getId(),
                cliente.getInfosPropriedade().getNome(),
                cliente.getLaboratorio().getId(),
                cliente.getLaboratorio().getNome(),
                cliente.getObservacoes()
        );
    }

    public static Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setDataInicial(dto.getDataInicial());
        cliente.setDataFinal(dto.getDataFinal());

        Propriedade propriedade = new Propriedade();
        propriedade.setId(dto.getPropriedadeId());
        propriedade.setNome(dto.getPropriedadeNome());

        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(dto.getLaboratorioId());
        laboratorio.setNome(dto.getLaboratorioNome());

        cliente.setInfosPropriedade(propriedade);
        cliente.setLaboratorio(laboratorio);
        cliente.setObservacoes(dto.getObservacoes());

        return cliente;
    }



}
