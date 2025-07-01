package com.perfulandia.usuarioservice.controller;

import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.service.UsuarioService;
import com.perfulandia.usuarioservice.service.UsuarioAggregationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioAggregationService aggregationService;

    @Test
    void testGetUsuarioPorId() throws Exception {
        Usuario usuario = new Usuario(1L, "Usuario Test", "usuario@test.com", "ADMIN");
        when(usuarioService.buscar(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Usuario Test"))
                .andExpect(jsonPath("$.correo").value("usuario@test.com"))
                .andExpect(jsonPath("$.rol").value("ADMIN"));
    }

    @Test
    void testDeleteUsuario() throws Exception {
        doNothing().when(usuarioService).eliminar(1L);
        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isOk());
    }
}
