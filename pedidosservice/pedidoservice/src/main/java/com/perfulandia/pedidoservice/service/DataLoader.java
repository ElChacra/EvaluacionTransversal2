package com.perfulandia.pedidoservice;

import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.repository.PedidoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PedidoRepository repo;

    public DataLoader(PedidoRepository repo){
        this.repo = repo;
    }

    @Override
    public void run(String... args){
        // Inserta 3 pedidos en la base de datos para pruebas
        repo.save(new Pedido(null, 1L, 10L, 2, "PENDIENTE", "Pedido de prueba 1", 12000.0));
        repo.save(new Pedido(null, 2L, 11L, 1, "ENVIADO", "Pedido de prueba 2", 25000.0));
        repo.save(new Pedido(null, 1L, 12L, 5, "PENDIENTE", "Pedido de prueba 3", 50000.0));
    }
}
