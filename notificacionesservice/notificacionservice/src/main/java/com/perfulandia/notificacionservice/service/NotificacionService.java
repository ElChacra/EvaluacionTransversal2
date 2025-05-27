package com.perfulandia.notificacionservice.service;

import com.perfulandia.notificacionservice.model.Notificacion;
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
        return repo.findAll();
    }

    public Notificacion guardar(Notificacion notificacion) {
        return repo.save(notificacion);
    }

    public Notificacion buscar(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    public List<Notificacion> listarPorUsuario(Long usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    // Nuevo método para guardar notificación desde DTO
    public NotificacionDTO guardarConDTO(NotificacionDTO dto) {
        Notificacion notificacion = new Notificacion();
        notificacion.setPedidoId(dto.getPedidoId());
        notificacion.setMensaje(dto.getMensaje());
        notificacion.setEstado(dto.getEstado());

        Notificacion guardada = repo.save(notificacion);

        return NotificacionDTO.builder()
                .id(guardada.getId())
                .pedidoId(guardada.getPedidoId())
                .mensaje(guardada.getMensaje())
                .estado(guardada.getEstado())
                .build();
    }
}
