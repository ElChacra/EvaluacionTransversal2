package com.perfulandia.pedidoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO { // Clase DTO para transferir datos de pedidos
    private Long id; // ID del pedido
    private Long usuarioId; // ID del usuario que realizó el pedido
    private Long productoId; // ID del producto pedido
    private Integer cantidad; // Cantidad del producto pedido
    private String estado; // Estado del pedido (ej. 'PENDIENTE', 'ENVIADO')
    private String descripcion; // Descripción opcional del pedido
    private Double total; // Total del pedido (precio x cantidad)
}
