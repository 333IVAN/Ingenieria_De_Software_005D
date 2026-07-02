package com.kiwi.service_sucursal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kiwi.service_sucursal.model.Sucursal;
import com.kiwi.service_sucursal.repository.SucursalRepository;

@ExtendWith(MockitoExtension.class)
class SucursalServiceTest {

    @Mock
    private SucursalRepository sucursalRepository;

    @InjectMocks
    private SucursalService sucursalService;

    @Test
    @DisplayName("Test para listar sucursales")
    void listarSucursalesTest() {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(1L);
        sucursal.setNombre("Sucursal norte");
        sucursal.setTelefono("999999999");
        sucursal.setCorreo("norte@test.cl");
        sucursal.setDireccionId(1L);

        when(sucursalRepository.findAll()).thenReturn(List.of(sucursal));

        List<Sucursal> resultado = sucursalService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Sucursal norte", resultado.get(0).getNombre());
        verify(sucursalRepository, times(1)).findAll();
    }
}
