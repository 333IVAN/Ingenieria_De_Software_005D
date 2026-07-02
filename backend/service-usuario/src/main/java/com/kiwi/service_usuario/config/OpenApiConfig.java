package com.kiwi.service_usuario.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API KiwiAyuda – Servicio Usuario")
                        .version("1.0")
                        .description("Documentación centralizada del servicio de usuarios, roles y asignación de roles de KiwiAyuda"))
                .servers(List.of(
                        new Server().url("http://localhost:9090").description("Servidor a través del Gateway")
                ));
    }
}
