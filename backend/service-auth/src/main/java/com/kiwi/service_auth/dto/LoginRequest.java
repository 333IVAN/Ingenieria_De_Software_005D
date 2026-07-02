package com.kiwi.service_auth.dto; 

import lombok.Data;

@Data
public class LoginRequest {
    private String rut;
    private String contrasena;
}