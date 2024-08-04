package com.clientes.service;

import com.clientes.model.dto.ClienteDTO;
import com.clientes.model.entity.Cliente;

public interface ICliente {

    Cliente save(ClienteDTO cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

}
