package com.example.api_gateway.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

    @Value("${jwt.secret}")
    private String secreto;

    public AuthenticationFilter(){
        super(Config.class);
    }

    public static class Config{}

    @Override
    public GatewayFilter apply(Config config){
        return (exchange, chain) -> {
            
            String path = exchange.getRequest().getURI().getPath();
            String method = exchange.getRequest().getMethod().name();

            boolean isDonacionPublica = //1
                    path.startsWith("/donaciones") &&
                    (method.equals("GET") || method.equals("POST"));

            boolean isUsuarioPublico = //2
                    path.startsWith("/usuarios") &&
                    (method.equals("GET") || method.equals("POST"));

            boolean isEventoPublico = //3
                    path.startsWith("/campanias") &&
                    method.equals("GET");

            boolean isMascotaPublico = //4
                    path.startsWith("/mascotas") &&
                    method.equals("GET"); 

            boolean isSucursalPublico = //5
                    path.startsWith("/sucursales") &&
                    method.equals("GET"); 

            //6. direccion queda publico
            //7. inventario queda privado
            //8. registro de notificaciones queda privado
            //9. auth queda publico

            boolean isTicketPublico = //10
                    path.startsWith("/tickets") &&
                    (method.equals("GET") || method.equals("POST"));

            boolean isSwagger =
                    path.contains("/v3/api-docs") ||
                    path.contains("/swagger-ui") ||
                    path.contains("/webjars");

            if (isDonacionPublica || isUsuarioPublico || isSwagger || isMascotaPublico || isEventoPublico || isSucursalPublico || isTicketPublico) {
                return chain.filter(exchange);
            }

            String authHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null) {
                return onError(exchange, "Token requerido", HttpStatus.UNAUTHORIZED);
            }
            
            String token = authHeader.substring(7);

            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(
                            Keys.hmacShaKeyFor(
                                Decoders.BASE64.decode(secreto)
                            )
                        )
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

               

                String authHeader1 = exchange.getRequest()
                        .getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION);

                if (authHeader1 == null || !authHeader1.startsWith("Bearer ")) {
                    return onError(exchange, "Token inválido.", HttpStatus.UNAUTHORIZED);
                }
                

                List<String> roles = claims.get("roles", List.class);

                if(roles == null){
                    return onError(exchange,"Sin roles",HttpStatus.FORBIDDEN);
                }

                boolean isAdmin = roles.contains("ROLE_ADMIN");
                boolean isVoluntario = roles.contains("ROLE_VOLUNTARIOC");
                boolean isUser = roles.contains("ROLE_USER");

                if (isAdmin) {
                    return chain.filter(exchange);
                }

                if (isVoluntario) {
                    if (path.startsWith("/donaciones") || path.startsWith("/inventarios") || path.startsWith("/tickets" ) || path.startsWith("/usuarios" )) {
                        return chain.filter(exchange);
                    }
                    return onError(exchange, "No autorizado", HttpStatus.FORBIDDEN);
                }

                if (isUser) {
                    if (path.startsWith("/donaciones") || path.startsWith("/tickets" )) {
                        return chain.filter(exchange);
                    }
                    return onError(exchange, "No autorizado", HttpStatus.FORBIDDEN);
                }

                return onError(exchange, "No autorizado", HttpStatus.FORBIDDEN);

            } catch(Exception e) {
                return onError(exchange, "Token inválido", HttpStatus.UNAUTHORIZED);
            }
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus){
        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }
}

