package com.kiwi.service_donacion.model;

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
@Table(name="donacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Donacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //opcional
    @NotNull
    @Column(nullable = false, length = 7)
    private Long cantidad;

    @NotBlank
    @Column(nullable = false)
    private String descripcion;

    @NotNull
    private LocalDate fechadonacion;
    
    @Transient
    private UsuarioDTO usuario; 

    @NotNull
    @Column(nullable = false)
    private Long usuarioId;

    @Transient
    private Insumo insumo;

    @NotNull
    @Column(nullable = false)
    private Long insumoId;
    
    @Transient
    private CampaniaDTO campana;

    @Column(nullable = true)
    private Long campanaId;
    
}
