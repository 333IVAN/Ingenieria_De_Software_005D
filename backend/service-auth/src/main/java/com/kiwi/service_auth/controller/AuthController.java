package com.kiwi.service_auth.controller; // Ajusta a tu paquete real

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kiwi.service_auth.dto.AuthResponse;
import com.kiwi.service_auth.dto.LoginRequest;
import com.kiwi.service_auth.dto.RegisterRequest;
import com.kiwi.service_auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Inicia sesión.", description = "Valida credenciales y devuelve token correspondiente.")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest.getRut(), loginRequest.getContrasena());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Operation(summary = "Registra nuevo usuario.", description = "Registra usuario y encripta contraseña.")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        String respuesta = authService.register(registerRequest);
        return ResponseEntity.ok(respuesta);
    }
}