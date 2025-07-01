package com.perfulandia.pedidoservice.controller;

import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testGetPedidoPorId() throws Exception {
        Pedido pedido = new Pedido(1L, 2L, 3L, 5, "PENDIENTE", "Pedido Test", 10000.0);
        when(pedidoService.buscar(1L)).thenReturn(pedido);

        mockMvc.perform(get("/api/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("PENDIENTE"))
                .andExpect(jsonPath("$.descripcion").value("Pedido Test"))
                .andExpect(jsonPath("$.total").value(10000.0));
    }

    @Test
    void testDeletePedido() throws Exception {
        doNothing().when(pedidoService).eliminar(1L);
        mockMvc.perform(delete("/api/pedidos/1"))
                .andExpect(status().isOk());
    }
}
