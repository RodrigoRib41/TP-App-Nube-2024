package com.appnube.clientes.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.appnube.clientes.model.entity.cliente;

public interface ClienteDAO extends CrudRepository<cliente, Integer> {

     
} 
