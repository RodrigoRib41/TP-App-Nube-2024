package com.clientes.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.clientes.model.entity.Cliente;

public interface ClienteDAO extends CrudRepository<Cliente, Integer> {

     
} 
