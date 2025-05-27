package com.perfulandia.notificacionservice.repository;

import com.perfulandia.notificacionservice.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    // nuevo método para listar notificaciones de un usuario
    List<Notificacion> findByUsuarioId(Long usuarioId);
}