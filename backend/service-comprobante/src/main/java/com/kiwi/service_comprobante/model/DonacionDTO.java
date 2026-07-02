package com.kiwi.service_comprobante.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonacionDTO {
    private Long cantidad;
    private String descripcion;
    private LocalDate fechadonacion;
    private Long insumoId;
}
