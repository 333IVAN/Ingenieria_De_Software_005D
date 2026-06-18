package com.kiwi.service_donacion.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaniaDTO {
    private Long id;
    private String descripcion;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    private Long idAdministrador;
    private Long idCoordinador1;
    private Long idCoordinador2;
    private Long idCoordinador3;
    private UsuarioDTO administrador;
    private UsuarioDTO coordinador; 
    private UsuarioDTO coordinador2; 
    private UsuarioDTO coordinador3;
}
