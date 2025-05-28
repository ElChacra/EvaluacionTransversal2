package com.perfulandia.pedidoservice.controller; // Paquete donde se encuentra este controlador

import com.perfulandia.pedidoservice.dto.PedidoDTO;
import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service; // Declara la dependencia al servicio de pedidos

    public PedidoController(PedidoService service) { // Constructor que inyecta la dependencia del servicio
        this.service = service; // Asigna el servicio inyectado a la variable de clase
    }

    @GetMapping // Maneja solicitudes GET a /api/pedidos
    public List<Pedido> listarTodos() { // Método que retorna la lista de todos los pedidos
        return service.listar(); // Llama al servicio para obtener todos los pedidos
    }

    @GetMapping("/usuario/{usuarioId}") // Maneja GET a /api/pedidos/usuario/{usuarioId}
    public List<Pedido> listarPorUsuario(@PathVariable Long usuarioId) { // Obtiene los pedidos por ID de usuario
        return service.listarPorUsuario(usuarioId); // Llama al servicio para filtrar por usuario
    }

    @PostMapping // Maneja solicitudes POST a /api/pedidos
    public Pedido crear(@RequestBody Pedido pedido) { // Crea un nuevo pedido a partir del JSON recibido
        return service.guardar(pedido); // Llama al servicio para guardar el pedido en la base de datos
    }

    @GetMapping("/{id}") // Maneja GET a /api/pedidos/{id}
    public Pedido buscar(@PathVariable Long id) { // Busca un pedido por su ID
        return service.buscar(id); // Llama al servicio para buscar el pedido
    }

    @DeleteMapping("/{id}") // Maneja DELETE a /api/pedidos/{id}
    public void eliminar(@PathVariable Long id) { // Elimina un pedido por su ID
        service.eliminar(id); // Llama al servicio para eliminar el pedido
    }

    @PostMapping("/dto") // Maneja POST a /api/pedidos/dto
    public PedidoDTO crearConDTO(@RequestBody PedidoDTO dto) { // Crea un pedido a partir de un DTO
        Pedido pedido = dtoToEntidad(dto); // Convierte el DTO en una entidad Pedido
        Pedido pedidoGuardado = service.guardar(pedido); // Guarda el pedido convertido
        return entidadToDTO(pedidoGuardado); // Devuelve el pedido guardado convertido a DTO
    }

    // Método auxiliar para convertir un DTO en una entidad Pedido
    private Pedido dtoToEntidad(PedidoDTO dto) {
        return Pedido.builder() // Usa el patrón builder para construir el objeto Pedido
                .id(dto.getId()) // Asigna el ID del DTO
                .usuarioId(dto.getUsuarioId()) // Asigna el ID del usuario
                .productoId(dto.getProductoId()) // Asigna el ID del producto
                .cantidad(dto.getCantidad()) // Asigna la cantidad
                .total(dto.getTotal()) // Asigna el total
                .build(); // Construye el objeto Pedido
    }

    // Método auxiliar para convertir una entidad Pedido a un DTO
    private PedidoDTO entidadToDTO(Pedido pedido) {
        return PedidoDTO.builder() // Usa el patrón builder para construir el objeto DTO
                .id(pedido.getId()) // Asigna el ID del pedido
                .usuarioId(pedido.getUsuarioId()) // Asigna el ID del usuario
                .productoId(pedido.getProductoId()) // Asigna el ID del producto
                .cantidad(pedido.getCantidad()) // Asigna la cantidad
                .total(pedido.getTotal()) // Asigna el total
                .build(); // Construye el objeto DTO
    }
}
