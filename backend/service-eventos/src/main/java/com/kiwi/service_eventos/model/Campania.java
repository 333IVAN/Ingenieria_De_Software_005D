package com.kiwi.service_eventos.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Campania")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Campania {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String descripcion;

    @NotNull
    @Column(name = "fecha_ini", nullable = false)
    private LocalDate fechaIni;

    @NotNull
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Transient
    private UsuarioDTO coordinador; 

    @NotNull
    @Column(name = "id_coordinador1", nullable = false)
    private Long idCoordinador1;

    @Transient
    private UsuarioDTO coordinador2; 
    //opcional
    @Column(name = "id_coordinador2", nullable = true)
    private Long idCoordinador2;

    @Transient
    private UsuarioDTO coordinador3; 

    @Column(name = "id_coordinador3", nullable = true)
    private Long idCoordinador3;

    @Transient
    private UsuarioDTO administrador;

    @NotNull
    @Column(name = "id_administrador", nullable = false)
    private Long idAdministrador;
}
