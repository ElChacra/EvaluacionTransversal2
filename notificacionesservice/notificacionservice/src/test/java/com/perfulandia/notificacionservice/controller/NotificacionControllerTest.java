package com.perfulandia.notificacionservice.controller;

import com.perfulandia.notificacionservice.model.Notificacion;
import com.perfulandia.notificacionservice.service.NotificacionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(NotificacionController.class)
public class NotificacionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificacionService notificacionService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testGetNotificacionPorId() throws Exception {
        Notificacion notificacion = new Notificacion(1L, 1L, "msg", "ENVIADO", null);
        when(notificacionService.buscar(1L)).thenReturn(notificacion);

        mockMvc.perform(get("/api/notificaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("msg"))
                .andExpect(jsonPath("$.estado").value("ENVIADO"));
    }
}
