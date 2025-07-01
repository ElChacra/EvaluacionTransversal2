package com.perfulandia.pedidoservice.service;

import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.repository.PedidoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PedidoServiceTest {
    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepo;

    public PedidoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Test 1 - Buscar todo")
    void testFindAll() {
        when(pedidoRepo.findAll()).thenReturn(List.of(new Pedido(1L, 2L, 3L, 5, "PENDIENTE", "Pedido Test", 15000.0)));
        List<Pedido> resultado = pedidoService.listar();
        assertEquals(1, resultado.size());
    }
}
