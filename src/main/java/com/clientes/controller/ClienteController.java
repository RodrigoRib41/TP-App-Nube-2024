package com.clientes.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.clientes.model.dto.ClienteDTO;
import com.clientes.model.entity.Cliente;
import com.clientes.model.payload.MensajeResponse;
import com.clientes.service.ICliente;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private ICliente clienteService;

    @PostMapping("cliente")
    public ResponseEntity<?> create(@RequestBody ClienteDTO clienteDto){
        Cliente clienteSave=null;
        try {
            clienteSave = clienteService.save(clienteDto);
            return new ResponseEntity<> (MensajeResponse.builder()
                                            .mensaje("Guardado Correctamente")
                                            .object(ClienteDTO.builder()
                                            .id(clienteSave.getId())
                                            .nombre(clienteSave.getNombre())
                                            .correo(clienteSave.getCorreo())
                                            .cuit(clienteSave.getCuit())
                                            .maxdescubierto(clienteSave.getMaxdescubierto())
                                            .build())
                                            .build()
                                        ,HttpStatus.CREATED);
        } catch (DataAccessException e) {
        return new ResponseEntity<>(MensajeResponse.builder()
                                            .mensaje(e.getMessage())
                                            .object(null)
                                            .build()
                                        , HttpStatus.METHOD_NOT_ALLOWED);
    }
}

    @PutMapping("cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDTO clienteDto, @PathVariable Integer id){
        Cliente clienteUpdate = null;

        try {
            Cliente clienteFind= clienteService.findById(id);
            if(clienteFind==null){
                return new ResponseEntity<>(MensajeResponse.builder()
                                            .mensaje("El registro no se encuentra en la base de datos")
                                            .object(null)
                                            .build()
                                        ,HttpStatus.METHOD_NOT_ALLOWED);
            
            }
            clienteUpdate = clienteService.save(clienteDto);
            return new ResponseEntity<> (MensajeResponse.builder()
                                            .mensaje("Guardado Correctamente")
                                            .object(ClienteDTO.builder()
                                            .id(clienteUpdate.getId())
                                            .nombre(clienteUpdate.getNombre())
                                            .correo(clienteUpdate.getCorreo())
                                            .cuit(clienteUpdate.getCuit())
                                            .maxdescubierto(clienteUpdate.getMaxdescubierto())
                                            .build())
                                            .build()
                                        , HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                                            .mensaje(e.getMessage())
                                            .object(null)
                                            .build()
                                        ,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            Cliente clienteDelete=clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete,HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                                            .mensaje(e.getMessage())
                                            .object(null)
                                            .build()
                                        ,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
            Cliente cliente = clienteService.findById(id);

            if(cliente==null){
            return new ResponseEntity<>(MensajeResponse.builder()
                                            .mensaje("El registro que intenta buscar no existe")
                                            .object(null)
                                            .build()
                                        ,HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(MensajeResponse.builder()
                                            .mensaje(null)
                                            .object(ClienteDTO.builder()
                                            .id(cliente.getId())
                                            .nombre(cliente.getNombre())
                                            .correo(cliente.getCorreo())
                                            .cuit(cliente.getCuit())
                                            .maxdescubierto(cliente.getMaxdescubierto())
                                            .build())
                                            .build()
                                        ,HttpStatus.OK);
            

    }

}
