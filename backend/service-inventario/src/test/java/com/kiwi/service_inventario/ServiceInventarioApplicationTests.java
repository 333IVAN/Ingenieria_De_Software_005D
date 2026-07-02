package com.kiwi.service_inventario;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class ServiceInventarioApplicationTests {
    @Test
    void sePuedeCrearLaAplicacion() {
        assertDoesNotThrow(ServiceInventarioApplication::new);
    }
}
