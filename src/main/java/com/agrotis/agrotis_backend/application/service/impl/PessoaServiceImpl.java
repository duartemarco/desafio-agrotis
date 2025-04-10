package com.agrotis.agrotis_backend.application.service.impl;

import com.agrotis.agrotis_backend.application.service.interfaces.PessoaService;
import com.agrotis.agrotis_backend.domain.model.Pessoa;
import com.agrotis.agrotis_backend.repository.PessoaRepository;

import java.util.Optional;


public class PessoaServiceImpl implements PessoaService {

    private PessoaRepository pessoaRepository;

    @Override
    public Optional<Pessoa> getPessoaById(Long id) {
        return pessoaRepository.findById(id);
    }


}
