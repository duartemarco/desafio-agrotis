package com.agrotis.agrotis_backend.application.service.interfaces;

import com.agrotis.agrotis_backend.domain.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ClienteService {

    Optional<Cliente> getClienteById(Long id);
}
