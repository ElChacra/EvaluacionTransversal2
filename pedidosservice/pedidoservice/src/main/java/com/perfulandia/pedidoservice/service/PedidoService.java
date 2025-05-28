package com.perfulandia.pedidoservice.service;

import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repo; // Repositorio inyectado

    public PedidoService(PedidoRepository repo) { // Constructor con inyección del repositorio
        this.repo = repo; // Asigna el repositorio a la variable de clase
    }

    public List<Pedido> listar() { // Lista todos los pedidos
        return repo.findAll(); // Llama al método JPA para obtener todos los registros
    }

    public Pedido guardar(Pedido pedido) { // Guarda un nuevo pedido
        return repo.save(pedido); // Llama al repositorio para persistirlo
    }

    public Pedido buscar(Long id) { // Busca un pedido por ID
        return repo.findById(id).orElse(null); // Devuelve el pedido o null si no existe
    }

    public void eliminar(Long id) { // Elimina un pedido por su ID
        repo.deleteById(id); // Llama al método del repositorio para borrar el registro
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) { // Lista pedidos por ID de usuario
        return repo.findByUsuarioId(usuarioId); // Usa el método personalizado del repositorio
    }
}
