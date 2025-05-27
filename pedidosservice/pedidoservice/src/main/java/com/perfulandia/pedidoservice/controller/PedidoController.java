package com.perfulandia.pedidoservice.controller;

import com.perfulandia.pedidoservice.dto.PedidoDTO;
import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return service.listar();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Pedido> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        return service.guardar(pedido);
    }

    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // Nuevo endpoint para crear usando DTO
    @PostMapping("/dto")
    public PedidoDTO crearConDTO(@RequestBody PedidoDTO dto) {
        Pedido pedido = dtoToEntidad(dto);
        Pedido pedidoGuardado = service.guardar(pedido);
        return entidadToDTO(pedidoGuardado);
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
