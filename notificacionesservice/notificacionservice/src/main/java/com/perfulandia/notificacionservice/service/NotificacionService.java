package com.perfulandia.notificacionservice.service;

import com.perfulandia.notificacionservice.model.Notificacion;
import com.perfulandia.notificacionservice.model.Usuario;
import com.perfulandia.notificacionservice.repository.NotificacionRepository;
import com.perfulandia.notificacionservice.dto.NotificacionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {

    private final NotificacionRepository repo;

    public NotificacionService(NotificacionRepository repo) {
        this.repo = repo;
    }

    public List<Notificacion> listar() {
        return repo.findAll(); // Devuelve todas las notificaciones
    }

    public Notificacion guardar(Notificacion notificacion) {
        return repo.save(notificacion); // Guarda una notificación completa (con usuario)
    }

    public Notificacion buscar(Long id) {
        return repo.findById(id).orElse(null); // Busca una notificación por ID
    }

    public void eliminar(Long id) {
        repo.deleteById(id); // Elimina una notificación por ID
    }

    public List<Notificacion> listarPorUsuario(Long usuarioId) {
        return repo.findByUsuarioId(usuarioId); // Busca notificaciones por ID de usuario
    }

    public NotificacionDTO guardarConDTO(NotificacionDTO dto) {
        Notificacion notificacion = new Notificacion(); // Crea nueva entidad
        notificacion.setPedidoId(dto.getPedidoId()); // Asigna pedidoId desde DTO
        notificacion.setMensaje(dto.getMensaje()); // Asigna mensaje
        notificacion.setEstado(dto.getEstado()); // Asigna estado

        // OJO: falta asignar usuario si es requerido

        Notificacion guardada = repo.save(notificacion); // Guarda en BD

        return NotificacionDTO.builder() // Devuelve DTO con datos guardados
                .id(guardada.getId())
                .pedidoId(guardada.getPedidoId())
                .mensaje(guardada.getMensaje())
                .estado(guardada.getEstado())
                .build();
    }
}
