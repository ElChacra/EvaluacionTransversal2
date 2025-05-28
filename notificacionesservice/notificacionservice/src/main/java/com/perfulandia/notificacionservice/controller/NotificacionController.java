package com.perfulandia.notificacionservice.controller;

import com.perfulandia.notificacionservice.model.Notificacion;
import com.perfulandia.notificacionservice.service.NotificacionService;
import com.perfulandia.notificacionservice.dto.NotificacionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones") // Ruta base para todos los endpoints de esta clase
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @GetMapping // GET /api/notificaciones
    public List<Notificacion> listar() {
        return service.listar(); // Retorna todas las notificaciones
    }

    @PostMapping // POST /api/notificaciones
    public Notificacion guardar(@RequestBody Notificacion notificacion) {
        return service.guardar(notificacion); // Guarda una nueva notificación
    }

    @GetMapping("/{id}") // GET /api/notificaciones/{id}
    public Notificacion buscar(@PathVariable Long id) {
        return service.buscar(id); // Busca una notificación por su ID
    }

    @DeleteMapping("/{id}") // DELETE /api/notificaciones/{id}
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id); // Elimina una notificación por su ID
    }

    @GetMapping("/usuario/{usuarioId}") // GET /api/notificaciones/usuario/{usuarioId}
    public List<Notificacion> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId); // Lista notificaciones de un usuario
    }

    @PostMapping("/dto") // POST /api/notificaciones/dto
    public ResponseEntity<NotificacionDTO> guardarConDTO(@RequestBody NotificacionDTO notificacionDTO) {
        NotificacionDTO dtoGuardado = service.guardarConDTO(notificacionDTO); // Guarda desde DTO y devuelve DTO
        return ResponseEntity.ok(dtoGuardado);
    }
}
