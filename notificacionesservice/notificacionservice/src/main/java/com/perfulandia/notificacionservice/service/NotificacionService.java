package com.perfulandia.notificacionservice.service;

import com.perfulandia.notificacionservice.model.Notificacion;
import com.perfulandia.notificacionservice.repository.NotificacionRepository;
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
}
