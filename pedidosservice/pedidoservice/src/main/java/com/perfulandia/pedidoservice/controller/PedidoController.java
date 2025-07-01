package com.perfulandia.pedidoservice.controller;

import com.perfulandia.pedidoservice.assembler.PedidoAssembler;
import com.perfulandia.pedidoservice.dto.PedidoDTO;
import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.service.PedidoService;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;
    private final PedidoAssembler assembler;

    public PedidoController(PedidoService service, PedidoAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    // HATEOAS: listar todos
    @GetMapping
    public CollectionModel<EntityModel<Pedido>> listarTodos() {
        List<EntityModel<Pedido>> pedidos = service.listar().stream()
                .map(assembler::toModel).toList();
        return CollectionModel.of(pedidos,
                linkTo(methodOn(PedidoController.class).listarTodos()).withSelfRel());
    }

    // HATEOAS: buscar por ID
    @GetMapping("/{id}")
    public EntityModel<Pedido> buscar(@PathVariable Long id) {
        Pedido p = service.buscar(id);
        return assembler.toModel(p);
    }

    // HATEOAS: crear nuevo
    @PostMapping
    public EntityModel<Pedido> crear(@RequestBody Pedido pedido) {
        Pedido guardado = service.guardar(pedido);
        return assembler.toModel(guardado);
    }

    // HATEOAS: eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // HATEOAS: listar por usuario
    @GetMapping("/usuario/{usuarioId}")
    public CollectionModel<EntityModel<Pedido>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<EntityModel<Pedido>> pedidos = service.listarPorUsuario(usuarioId).stream()
                .map(assembler::toModel).toList();
        return CollectionModel.of(pedidos,
                linkTo(methodOn(PedidoController.class).listarPorUsuario(usuarioId)).withSelfRel());
    }

    // Endpoint DTO
    @PostMapping("/dto")
    public ResponseEntity<PedidoDTO> crearConDTO(@RequestBody PedidoDTO dto) {
        Pedido pedido = dtoToEntidad(dto);
        Pedido pedidoGuardado = service.guardar(pedido);
        PedidoDTO dtoGuardado = entidadToDTO(pedidoGuardado);
        return ResponseEntity.ok(dtoGuardado);
    }

    // Métodos auxiliares para convertir entre DTO y entidad
    private Pedido dtoToEntidad(PedidoDTO dto) {
        return Pedido.builder()
                .id(dto.getId())
                .usuarioId(dto.getUsuarioId())
                .productoId(dto.getProductoId())
                .cantidad(dto.getCantidad())
                .total(dto.getTotal())
                .build();
    }

    private PedidoDTO entidadToDTO(Pedido pedido) {
        return PedidoDTO.builder()
                .id(pedido.getId())
                .usuarioId(pedido.getUsuarioId())
                .productoId(pedido.getProductoId())
                .cantidad(pedido.getCantidad())
                .total(pedido.getTotal())
                .build();
    }
}
