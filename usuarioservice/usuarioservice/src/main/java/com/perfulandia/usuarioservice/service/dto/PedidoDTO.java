package com.perfulandia.usuarioservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa un Pedido recibido desde PedidoService
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO {
    private Long id;
    private Long usuarioId;
    private Long productoId;
    private int cantidad;
    private double total;
}
