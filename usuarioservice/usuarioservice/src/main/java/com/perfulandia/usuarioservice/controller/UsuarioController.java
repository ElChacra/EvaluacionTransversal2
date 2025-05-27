package com.perfulandia.usuarioservice.controller;

import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.service.UsuarioService;
import com.perfulandia.usuarioservice.service.UsuarioAggregationService;
import com.perfulandia.usuarioservice.service.dto.UserOverviewDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioAggregationService aggregationService;

    // Constructor con ambos servicios
    public UsuarioController(UsuarioService service, UsuarioAggregationService aggregationService) {
        this.service = service;
        this.aggregationService = aggregationService;
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return service.guardar(usuario);
    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable long id) {
        return service.buscar(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable long id) {
        service.eliminar(id);
    }

    // Nuevo endpoint: Overview del usuario
    @GetMapping("/{id}/overview")
    public UserOverviewDTO obtenerResumenUsuario(@PathVariable long id) {
        return aggregationService.getUserOverview(id);
    }
}
