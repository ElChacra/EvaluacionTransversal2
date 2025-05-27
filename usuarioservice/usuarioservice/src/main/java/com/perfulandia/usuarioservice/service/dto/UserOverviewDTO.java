package com.perfulandia.usuarioservice.service.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;

/**
 * DTO agregado que reúne pedidos y notificaciones de un usuario,
 * además de sus conteos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOverviewDTO {
    private List<PedidoDTO> pedidos;
    private List<NotificacionDTO> notificaciones;
    private int pedidosCount;
    private int notificacionesCount;
}