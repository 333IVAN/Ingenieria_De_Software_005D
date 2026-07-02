package com.kiwi.service_inventario.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kiwi.service_inventario.model.Inventario;
import com.kiwi.service_inventario.repository.InventarioRepository;

@ExtendWith(MockitoExtension.class)
class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @Test
    @DisplayName("Test para crear inventario")
    void crearInventarioTest() {
        Inventario inventario = new Inventario();
        inventario.setInsumoId(1L);
        inventario.setDescripcion("Alimentos");
        inventario.setUnidad("cajas");
        inventario.setStock(100L);

        when(inventarioRepository.save(any(Inventario.class))).thenAnswer(invocation -> {
            Inventario i = invocation.getArgument(0);
            i.setId(1L);
            return i;
        });

        Inventario resultado = inventarioService.crear(inventario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(100L, resultado.getStock());
        verify(inventarioRepository, times(1)).save(inventario);
    }
}
