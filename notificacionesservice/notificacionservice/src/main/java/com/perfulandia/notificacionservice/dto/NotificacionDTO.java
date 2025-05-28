package com.perfulandia.notificacionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificacionDTO {
    private Long id; // ID de la notificación
    private Long pedidoId; // ID del pedido
    private String mensaje; // Contenido del mensaje
    private String estado; // Estado de la notificación
}
