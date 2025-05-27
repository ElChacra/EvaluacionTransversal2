package com.perfulandia.notificacionservice.controller;

import com.perfulandia.notificacionservice.model.Notificacion;
import com.perfulandia.notificacionservice.service.NotificacionService;
import com.perfulandia.notificacionservice.dto.NotificacionDTO; // Importa el DTO
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/usuario/{usuarioId}")
    public List<Notificacion> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    // Nuevo endpoint para crear notificación con DTO
    @PostMapping("/dto")
    public ResponseEntity<NotificacionDTO> guardarConDTO(@RequestBody NotificacionDTO notificacionDTO) {
        NotificacionDTO dtoGuardado = service.guardarConDTO(notificacionDTO);
        return ResponseEntity.ok(dtoGuardado);
    }
}
