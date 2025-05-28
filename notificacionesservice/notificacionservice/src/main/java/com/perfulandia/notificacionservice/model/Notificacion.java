package com.perfulandia.notificacionservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
    private Long id;

    private Long pedidoId; // ID del pedido relacionado
    private String mensaje; // Contenido de la notificación
    private String estado; // ENVIADA, PENDIENTE, ERROR

    @ManyToOne // Muchas notificaciones pueden pertenecer a un usuario
    @JoinColumn(name = "usuario_id", nullable = false) // Clave foránea obligatoria
    private Usuario usuario;
}
