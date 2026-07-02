package com.kiwi.service_inventario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient donacionWebClient() {
        return WebClient.builder()
            .baseUrl("http://localhost:9090/donaciones")
            .build();
    }
}
