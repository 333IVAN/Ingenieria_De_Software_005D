package com.kiwi.service_usuario.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kiwi.service_usuario.model.Usuario;
import com.kiwi.service_usuario.model.UsuarioRol;
import com.kiwi.service_usuario.repository.UsuarioRepository;
import com.kiwi.service_usuario.repository.UsuarioRolRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioRolRepository usuarioRolRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Test para crear usuario")
    void crearUsuarioTest() {

        Usuario usuario = new Usuario();
        usuario.setRut("12345678");
        usuario.setDv("9");
        usuario.setPnombre("Ana");
        usuario.setAppaterno("Perez");
        usuario.setTelefono("999999999");
        usuario.setCorreo("ana@test.cl");
        usuario.setDireccionId(1L);
        usuario.setContrasena("1234");

        when(usuarioRepository.findByRutAndDv("12345678", "9"))
            .thenReturn(Optional.empty());

        when(usuarioRepository.save(any(Usuario.class)))
            .thenAnswer(invocation -> {
                Usuario u = invocation.getArgument(0);
                u.setId(1L);
                return u;
            });

        Usuario resultado = usuarioService.crear(usuario, "token");

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Ana", resultado.getPnombre());

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(usuarioRolRepository, times(1)).save(any(UsuarioRol.class));
    }
}
