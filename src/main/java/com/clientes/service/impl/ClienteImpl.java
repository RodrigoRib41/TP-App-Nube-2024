package com.clientes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientes.model.dao.ClienteDAO;
import com.clientes.model.dto.ClienteDTO;
import com.clientes.model.entity.Cliente;
import com.clientes.service.ICliente;

@Service
public class ClienteImpl implements ICliente{

    @Autowired
    private ClienteDAO clienteDAO;


    @Transactional
    @Override
    public Cliente save(ClienteDTO clienteDto) {
        Cliente cliente = Cliente.builder()
                        .id(clienteDto.getId())
                        .nombre(clienteDto.getNombre())
                        .correo(clienteDto.getCorreo())
                        .cuit(clienteDto.getCuit())
                        .maxdescubierto(clienteDto.getMaxdescubierto())
                        .build();
        return clienteDAO.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDAO.findById(id).orElse(null);
    }
    
    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDAO.delete(cliente);
    }

}
