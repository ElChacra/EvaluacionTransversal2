package com.perfulandia.notificacionservice.controller;

import com.perfulandia.notificacionservice.model.Notificacion;
import com.perfulandia.notificacionservice.service.NotificacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Notificacion> listar() {
        return service.listar();
    }

    @PostMapping
    public Notificacion guardar(@RequestBody Notificacion notificacion) {
        return service.guardar(notificacion);
    }

    @GetMapping("/{id}")
    public Notificacion buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
