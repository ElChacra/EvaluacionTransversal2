package com.perfulandia.pedidoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto { // Clase que representa un producto (podría venir del microservicio de productos)
    private long id; // ID del producto
    private String nombre; // Nombre del producto
    private double precio; // Precio del producto
    private int stock; // Cantidad en stock
}
