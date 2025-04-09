package com.agrotis.agrotis_backend.application.controller;

import com.agrotis.agrotis_backend.application.service.interfaces.ClienteService;
import com.agrotis.agrotis_backend.domain.model.Cliente;
import com.agrotis.agrotis_backend.application.dto.ClienteDTO;
import com.agrotis.agrotis_backend.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteService clienteService, ClienteRepository clienteRepository) {
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ClienteDTO addCliente(@RequestBody ClienteDTO dto) {
        Cliente cliente = toEntity(dto);
        return clienteRepository.save(cliente);
    }

    @GetMapping("/consultar/{id}")
    public Optional<Cliente> consultarCliente(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }


}
