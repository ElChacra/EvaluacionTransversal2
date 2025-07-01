package com.perfulandia.usuarioservice.service;
import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepo;

    public UsuarioServiceTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Test 1 - Buscar todo")
    void testFindAll() {
        when(usuarioRepo.findAll()).thenReturn(List.of(new Usuario(1L, "Usuario Test", "usuario@test.com", "ADMIN")));
        List<Usuario> resultado = usuarioService.listar();
        assertEquals(1, resultado.size());
        verify(usuarioRepo).findAll(); // Esta línea cumple el último requisito
    }
}
