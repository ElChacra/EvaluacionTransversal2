package com.perfulandia.notificacionservice.service;

import com.perfulandia.notificacionservice.model.Notificacion;
import com.perfulandia.notificacionservice.repository.NotificacionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotificacionServiceTest {
    @InjectMocks
    private NotificacionService notificacionService;

    @Mock
    private NotificacionRepository notificacionRepo;

    public NotificacionServiceTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Test 1 - Buscar todo")
    void testFindAll() {
        when(notificacionRepo.findAll()).thenReturn(List.of(new Notificacion(1L, 1L, "Testeando Notificacion", "ENVIADO", null)));
        List<Notificacion> resultado = notificacionService.listar();
        assertEquals(1, resultado.size());
        verify(notificacionRepo).findAll();
    }
}
