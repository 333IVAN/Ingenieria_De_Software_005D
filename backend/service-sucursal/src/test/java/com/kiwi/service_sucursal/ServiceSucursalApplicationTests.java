package com.kiwi.service_sucursal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class ServiceSucursalApplicationTests {
    @Test
    void sePuedeCrearLaAplicacion() {
        assertDoesNotThrow(ServiceSucursalApplication::new);
    }
}
