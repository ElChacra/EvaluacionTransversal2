package com.perfulandia.pedidoservice.repository;

import com.perfulandia.pedidoservice.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Nuevo método para buscar por usuarioId
    List<Pedido> findByUsuarioId(Long usuarioId);
}