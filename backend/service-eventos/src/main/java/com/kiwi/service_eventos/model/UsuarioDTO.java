package com.kiwi.service_eventos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String rut;
    private String dv;
    private String pnombre;
    private String snombre;
    private String appaterno;
    private String apmaterno;
    private String telefono;
    private String correo;
}
