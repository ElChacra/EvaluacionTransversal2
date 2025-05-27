package com.perfulandia.pedidoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private long id;
    private String nombre;
    private double precio;
    private int stock;
}
