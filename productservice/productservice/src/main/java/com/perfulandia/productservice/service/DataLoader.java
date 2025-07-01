package com.perfulandia.productservice.service;

import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository repo;

    public DataLoader(ProductoRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.save(new Producto(0L, "Perfume Invierno", 15990.0, 20));
        repo.save(new Producto(0L, "Perfume Verano", 12990.0, 30));
        repo.save(new Producto(0L, "Perfume Otoño", 18990.0, 10));
    }
}
