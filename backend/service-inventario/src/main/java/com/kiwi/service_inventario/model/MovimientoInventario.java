package com.kiwi.service_inventario.model;

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
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="movimiento_inventario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String tipo;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Long cantidad;

    @Column(nullable = false)
    private LocalDate fechaMovimiento;

    private String descripcion;

    @NotNull
    @Column(nullable = false)
    private Long inventarioId;

    @Transient
    private Inventario inventario;

    private Long donacionId;

    @Transient
    private DonacionDTO donacion;
}
