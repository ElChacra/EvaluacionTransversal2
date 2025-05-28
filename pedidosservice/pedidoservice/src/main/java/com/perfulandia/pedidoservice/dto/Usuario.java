package com.perfulandia.pedidoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario { // Clase que representa un usuario (podría venir del microservicio de usuarios)
    private long id; // ID del usuario
    private String nombre; // Nombre del usuario
    private String correo; // Correo del usuario
    private String rol; // Rol del usuario (ej. 'cliente', 'admin')
}
