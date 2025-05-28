package com.perfulandia.pedidoservice.repository;

import com.perfulandia.pedidoservice.model.Pedido; // Importa la entidad Pedido
import org.springframework.data.jpa.repository.JpaRepository; // Interfaz para repositorios JPA
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> { // Repositorio JPA para la entidad Pedido
    List<Pedido> findByUsuarioId(Long usuarioId); // Método personalizado para buscar pedidos por usuario
}
