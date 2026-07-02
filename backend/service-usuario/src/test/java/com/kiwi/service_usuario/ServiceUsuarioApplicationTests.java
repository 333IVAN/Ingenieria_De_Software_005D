package com.kiwi.service_usuario;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class ServiceUsuarioApplicationTests {

	@Test
	void sePuedeCrearLaAplicacion() {
		assertDoesNotThrow(ServiceUsuarioApplication::new);
	}

}
