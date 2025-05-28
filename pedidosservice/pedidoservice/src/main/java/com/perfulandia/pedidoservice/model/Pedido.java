package com.perfulandia.pedidoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido { // Clase que representa la tabla "pedido" en la base de datos
    @Id // Campo ID primario
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El ID se genera automáticamente (autoincremental)
    private Long id; // ID del pedido

    private Long usuarioId; // ID del usuario que hizo el pedido
    private Long productoId; // ID del producto comprado
    private Integer cantidad; // Cantidad del producto en el pedido
    private String estado; // Estado del pedido (ej. pendiente, enviado)
    private String descripcion; // Descripción del pedido
    private Double total; // Total monetario del pedido
}
