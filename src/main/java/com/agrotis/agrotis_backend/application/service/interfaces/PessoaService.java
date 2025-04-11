package com.agrotis.agrotis_backend.application.service.interfaces;

import com.agrotis.agrotis_backend.application.dto.PessoaRequestDTO;
import com.agrotis.agrotis_backend.application.dto.PessoaDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PessoaService {

    PessoaDTO getPessoaById(Long id);

    PessoaDTO addPessoa(PessoaRequestDTO pessoaDTO);

    List<PessoaDTO> listarPessoas();

    void deletePessoaById(Long id);

    PessoaDTO atualizarPessoa(Long id, @Valid PessoaRequestDTO dto);

}
