package com.kiwi.service_auth.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String rut;
    private String correo;
    private String contrasena;
}