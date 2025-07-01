package com.perfulandia.usuarioservice.controller;

import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.service.UsuarioService;
import com.perfulandia.usuarioservice.service.UsuarioAggregationService;
import com.perfulandia.usuarioservice.assembler.UsuarioAssembler;
import com.perfulandia.usuarioservice.service.dto.UserOverviewDTO;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioAggregationService aggregationService;
    private final UsuarioAssembler assembler;

    // Constructor con ambos servicios y assembler
    public UsuarioController(UsuarioService service, UsuarioAggregationService aggregationService, UsuarioAssembler assembler) {
        this.service = service;
        this.aggregationService = aggregationService;
        this.assembler = assembler;
    }

    // HATEOAS: listar todos
    @GetMapping
    public CollectionModel<EntityModel<Usuario>> listar() {
        List<EntityModel<Usuario>> usuarios = service.listar().stream()
                .map(assembler::toModel).toList();
        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioController.class).listar()).withSelfRel());
    }

    // HATEOAS: buscar por ID
    @GetMapping("/{id}")
    public EntityModel<Usuario> buscar(@PathVariable long id) {
        Usuario usuario = service.buscar(id);
        return assembler.toModel(usuario);
    }

    // HATEOAS: guardar
    @PostMapping
    public EntityModel<Usuario> guardar(@RequestBody Usuario usuario) {
        Usuario guardado = service.guardar(usuario);
        return assembler.toModel(guardado);
    }

    // HATEOAS: eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint: Overview del usuario (no cambia, es solo DTO)
    @GetMapping("/{id}/overview")
    public UserOverviewDTO obtenerResumenUsuario(@PathVariable long id) {
        return aggregationService.getUserOverview(id);
    }
}
