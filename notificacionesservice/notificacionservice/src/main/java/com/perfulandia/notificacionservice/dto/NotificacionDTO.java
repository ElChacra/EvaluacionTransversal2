package com.perfulandia.notificacionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que mapea la respuesta de NotificacionService
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificacionDTO {
    private Long id;
    private Long pedidoId;
    private String mensaje;
    private String estado;  // p.ej. "ENVIADA", "ERROR", "PENDIENTE"
}