package com.agrotis.agrotis_backend.application.service.interfaces;

import com.agrotis.agrotis_backend.domain.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PessoaService {

    Optional<Pessoa> getPessoaById(Long id);
    Pessoa addPessoa(Pessoa pessoa);
}
