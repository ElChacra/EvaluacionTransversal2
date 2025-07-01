package com.perfulandia.productservice.service;

import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.repository.ProductoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoServiceTest {
    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepo;

    public ProductoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Test 1 - Buscar todo")
    void testFindAll() {
        when(productoRepo.findAll()).thenReturn(List.of(new Producto(1L, "Producto Test", 999.99, 100)));
        List<Producto> resultado = productoService.listar();
        assertEquals(1, resultado.size());
        verify(productoRepo).findAll();
    }
}
