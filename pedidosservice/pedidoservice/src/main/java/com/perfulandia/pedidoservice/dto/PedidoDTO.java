package com.perfulandia.pedidoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO {
    private Long id;
    private Long usuarioId;
    private Long productoId;
    private Integer cantidad;
    private String estado;
    private String descripcion;
    private Double total;
}
