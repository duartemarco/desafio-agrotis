package com.agrotis.agrotis_backend.application.mapper;

import com.agrotis.agrotis_backend.application.dto.AddPessoaDTO;
import com.agrotis.agrotis_backend.application.dto.LaboratorioDTO;
import com.agrotis.agrotis_backend.application.dto.PessoaDTO;
import com.agrotis.agrotis_backend.application.dto.PropriedadeDTO;
import com.agrotis.agrotis_backend.domain.model.Pessoa;
import com.agrotis.agrotis_backend.domain.model.Laboratorio;
import com.agrotis.agrotis_backend.domain.model.Propriedade;

public class Mapper {

    public static PessoaDTO toDTO(Pessoa pessoa) {

        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setDataInicial(pessoa.getDataInicial());
        pessoaDTO.setDataFinal(pessoa.getDataFinal());

        if (pessoa.getInfosPropriedade() != null) {
            PropriedadeDTO propriedadeDTO = new PropriedadeDTO();
            propriedadeDTO.setId(pessoa.getInfosPropriedade().getId());
            propriedadeDTO.setNome(pessoa.getInfosPropriedade().getNome());
            pessoaDTO.setInfosPropriedade(propriedadeDTO);
        }

        if (pessoa.getLaboratorio() != null) {
            LaboratorioDTO laboratorioDTO = new LaboratorioDTO();
            laboratorioDTO.setNome(pessoa.getLaboratorio().getNome());
            pessoaDTO.setLaboratorio(laboratorioDTO);
        }

        pessoaDTO.setObservacoes(pessoa.getObservacoes());

        return pessoaDTO;

    }

    public static Pessoa toEntity(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setDataInicial(dto.getDataInicial());
        pessoa.setDataFinal(dto.getDataFinal());

        if (dto.getInfosPropriedade() != null) {
            Propriedade propriedade = new Propriedade();
            propriedade.setId(dto.getInfosPropriedade().getId());
            propriedade.setNome(dto.getInfosPropriedade().getNome());
            pessoa.setInfosPropriedade(propriedade);
        }

        if (dto.getLaboratorio() != null) {
            Laboratorio laboratorio = new Laboratorio();
            laboratorio.setNome(dto.getLaboratorio().getNome());
            pessoa.setLaboratorio(laboratorio);
        }

        pessoa.setObservacoes(dto.getObservacoes());

        return pessoa;
    }

    public static Pessoa toEntity(AddPessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setDataInicial(dto.getDataInicial());
        pessoa.setDataFinal(dto.getDataFinal());

        if (dto.getIdPropriedade() != null) {
            Propriedade propriedade = new Propriedade();
            propriedade.setId(dto.getIdPropriedade());
            pessoa.setInfosPropriedade(propriedade);
        }

        if (dto.getIdLaboratorio() != null) {
            Laboratorio laboratorio = new Laboratorio();
            pessoa.setLaboratorio(laboratorio);
        }

        pessoa.setObservacoes(dto.getObservacoes());

        return pessoa;
    }

    public static LaboratorioDTO toDTO(Laboratorio laboratorio) {
        LaboratorioDTO laboratorioDTO = new LaboratorioDTO();
        laboratorioDTO.setNome(laboratorio.getNome());
        return laboratorioDTO;
    }

    public static Laboratorio toEntity(LaboratorioDTO laboratorioDTO) {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setNome(laboratorioDTO.getNome());
        return laboratorio;
    }

}
