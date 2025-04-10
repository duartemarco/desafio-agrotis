package com.agrotis.agrotis_backend.application.mapper;

import com.agrotis.agrotis_backend.application.dto.PessoaDTO;
import com.agrotis.agrotis_backend.domain.model.Pessoa;
import com.agrotis.agrotis_backend.domain.model.Laboratorio;
import com.agrotis.agrotis_backend.domain.model.Propriedade;

public class PessoaMapper {

    public static PessoaDTO toDTO(Pessoa pessoa) {
        return new PessoaDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getDataFinal(),
                pessoa.getDataInicial(),
                pessoa.getInfosPropriedade().getId(),
                pessoa.getInfosPropriedade().getNome(),
                pessoa.getLaboratorio().getId(),
                pessoa.getLaboratorio().getNome(),
                pessoa.getObservacoes()
        );
    }

    public static Pessoa toEntity(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setDataInicial(dto.getDataInicial());
        pessoa.setDataFinal(dto.getDataFinal());

        Propriedade propriedade = new Propriedade();
        propriedade.setId(dto.getPropriedadeId());
        propriedade.setNome(dto.getPropriedadeNome());

        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(dto.getLaboratorioId());
        laboratorio.setNome(dto.getLaboratorioNome());

        pessoa.setInfosPropriedade(propriedade);
        pessoa.setLaboratorio(laboratorio);
        pessoa.setObservacoes(dto.getObservacoes());

        return pessoa;
    }



}
