package com.clientes.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clientes.model.dto.ClienteDTO;
import com.clientes.model.entity.Cliente;
import com.clientes.service.ICliente;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private ICliente clienteService;

    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO create(@RequestBody ClienteDTO clienteDto){
        Cliente clienteSave = clienteService.save(clienteDto);
        return ClienteDTO.builder()
        .id(clienteSave.getId())
        .nombre(clienteSave.getNombre())
        .correo(clienteSave.getCorreo())
        .cuit(clienteSave.getCuit())
        .maxdescubierto(clienteSave.getMaxdescubierto())
        .build();
    }

    @PutMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO update(@RequestBody ClienteDTO clienteDto){
        Cliente clienteUpdate = clienteService.save(clienteDto);
        return ClienteDTO.builder()
        .id(clienteUpdate.getId())
        .nombre(clienteUpdate.getNombre())
        .correo(clienteUpdate.getCorreo())
        .cuit(clienteUpdate.getCuit())
        .maxdescubierto(clienteUpdate.getMaxdescubierto())
        .build();

    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String,Object> responses = new HashMap<>();
        try {
            Cliente clienteDelete=clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete,HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            responses.put("Mensaje", e.getMessage());
            responses.put("Cliente", null);
            return new ResponseEntity<>(responses,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO showById(@PathVariable Integer id){
            Cliente cliente = clienteService.findById(id);
            return ClienteDTO.builder()
            .id(cliente.getId())
            .nombre(cliente.getNombre())
            .correo(cliente.getCorreo())
            .cuit(cliente.getCuit())
            .maxdescubierto(cliente.getMaxdescubierto())
            .build();

    }

}
