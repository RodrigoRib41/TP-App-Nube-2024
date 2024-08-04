package com.clientes.model.dto;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ClienteDTO implements Serializable {

    private Integer id;
    private String nombre;
    private String correo;
    private BigInteger cuit;
    private Integer maxdescubierto;
}
