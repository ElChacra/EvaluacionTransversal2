package com.perfulandia.pedidoservice.service;

import com.perfulandia.pedidoservice.dto.Producto;
import com.perfulandia.pedidoservice.dto.Usuario;
import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repo;
    private final RestTemplate restTemplate;

    public PedidoService(PedidoRepository repo, RestTemplate restTemplate) {
        this.repo = repo;
        this.restTemplate = restTemplate;
    }

    public List<Pedido> listar() {
        return repo.findAll();
    }

    public Pedido guardar(Pedido pedido) {
        // Validar usuario
        Usuario usuario = restTemplate.getForObject("http://localhost:8081/api/usuarios/" + pedido.getUsuarioId(), Usuario.class);
        if (usuario == null) throw new RuntimeException("Usuario no encontrado");

        // Validar producto
        Producto producto = restTemplate.getForObject("http://localhost:8080/api/productos/" + pedido.getProductoId(), Producto.class);
        if (producto == null || producto.getStock() < pedido.getCantidad())
            throw new RuntimeException("Producto no disponible");

        double total = producto.getPrecio() * pedido.getCantidad();
        pedido.setTotal(total);

        return repo.save(pedido);
    }

    public Pedido buscar(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
