package com.perfulandia.usuarioservice;

import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository repo;

    public DataLoader(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        // Borra todos los usuarios antes de cargar nuevos (solo para pruebas/desarrollo)
        repo.deleteAll();

        // Carga de datos iniciales para pruebas, el ID se autogenera
        repo.save(new Usuario(null, "admin", "admin@perfulandia.com", "ADMIN"));
        repo.save(new Usuario(null, "gerente", "gerente@perfulandia.com", "GERENTE"));
        repo.save(new Usuario(null, "usuario", "usuario@perfulandia.com", "Usuario"));
    }
}
