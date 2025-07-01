package com.perfulandia.notificacionservice.controller;

import com.perfulandia.notificacionservice.assembler.NotificacionAssembler;
import com.perfulandia.notificacionservice.model.Notificacion;
import com.perfulandia.notificacionservice.service.NotificacionService;
import com.perfulandia.notificacionservice.dto.NotificacionDTO;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionService service;
    private final NotificacionAssembler assembler;

    public NotificacionController(NotificacionService service, NotificacionAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    // HATEOAS: listar todas
    @GetMapping
    public CollectionModel<EntityModel<Notificacion>> listar() {
        List<EntityModel<Notificacion>> notificaciones = service.listar().stream()
                .map(assembler::toModel).toList();
        return CollectionModel.of(notificaciones,
                linkTo(methodOn(NotificacionController.class).listar()).withSelfRel());
    }

    // HATEOAS: buscar por ID
    @GetMapping("/{id}")
    public EntityModel<Notificacion> buscar(@PathVariable Long id) {
        Notificacion n = service.buscar(id);
        return assembler.toModel(n);
    }

    // HATEOAS: guardar nueva
    @PostMapping
    public EntityModel<Notificacion> guardar(@RequestBody Notificacion notificacion) {
        Notificacion guardada = service.guardar(notificacion);
        return assembler.toModel(guardada);
    }

    // HATEOAS: eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // HATEOAS: listar por usuario
    @GetMapping("/usuario/{usuarioId}")
    public CollectionModel<EntityModel<Notificacion>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<EntityModel<Notificacion>> notificaciones = service.listarPorUsuario(usuarioId).stream()
                .map(assembler::toModel).toList();
        return CollectionModel.of(notificaciones,
                linkTo(methodOn(NotificacionController.class).listarPorUsuario(usuarioId)).withSelfRel());
    }

    // Endpoint DTO
    @PostMapping("/dto")
    public ResponseEntity<NotificacionDTO> guardarConDTO(@RequestBody NotificacionDTO notificacionDTO) {
        NotificacionDTO dtoGuardado = service.guardarConDTO(notificacionDTO);
        return ResponseEntity.ok(dtoGuardado);
    }
}
