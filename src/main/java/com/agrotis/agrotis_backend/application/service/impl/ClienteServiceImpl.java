package com.agrotis.agrotis_backend.application.service.impl;

import com.agrotis.agrotis_backend.application.service.interfaces.ClienteService;
import com.agrotis.agrotis_backend.domain.model.Cliente;
import com.agrotis.agrotis_backend.repository.ClienteRepository;

import java.util.Optional;


public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    @Override
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }


}
