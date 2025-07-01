package com.perfulandia.notificacionservice;

import com.perfulandia.notificacionservice.model.Notificacion;
import com.perfulandia.notificacionservice.model.Usuario;
import com.perfulandia.notificacionservice.repository.NotificacionRepository;
import com.perfulandia.notificacionservice.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final NotificacionRepository notificacionRepo;
    private final UsuarioRepository usuarioRepo;

    public DataLoader(NotificacionRepository notificacionRepo, UsuarioRepository usuarioRepo) {
        this.notificacionRepo = notificacionRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public void run(String... args) {
        // 1. Crear y guardar usuarios
        Usuario u1 = new Usuario();
        u1.setNombre("Manuel");
        usuarioRepo.save(u1);

        Usuario u2 = new Usuario();
        u2.setNombre("Esteban");
        usuarioRepo.save(u2);

        // 2. Crear y guardar notificaciones asociadas
        notificacionRepo.save(new Notificacion(null, 100L, "Notificación 1: Pedido enviado", "ENVIADA", u1));
        notificacionRepo.save(new Notificacion(null, 101L, "Notificación 2: Pedido en error", "ERROR", u2));
        notificacionRepo.save(new Notificacion(null, 102L, "Notificación 3: Pedido pendiente", "PENDIENTE", u1));
    }
}
