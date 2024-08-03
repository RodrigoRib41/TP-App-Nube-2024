package com.clientes.service;

import com.clientes.model.entity.Cliente;

public interface ICliente {

    Cliente save(Cliente cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

}
