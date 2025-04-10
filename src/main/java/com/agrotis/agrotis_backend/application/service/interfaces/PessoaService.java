package com.agrotis.agrotis_backend.application.service.interfaces;

import com.agrotis.agrotis_backend.application.dto.PessoaDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PessoaService {

    Optional<PessoaDTO> getPessoaById(Long id);

    PessoaDTO addPessoa(PessoaDTO pessoaDTO);

    List<PessoaDTO> listarPessoas();

    void deletePessoaById(Long id);

    Optional<PessoaDTO> atualizarPessoa(Long id, @Valid PessoaDTO dto);

}
