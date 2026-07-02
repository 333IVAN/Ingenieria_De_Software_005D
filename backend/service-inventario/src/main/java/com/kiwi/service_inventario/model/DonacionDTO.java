package com.kiwi.service_inventario.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonacionDTO {
    private Long id;
    private Long cantidad;
    private String descripcion;
    private LocalDate fechadonacion;
    private Long usuarioId;
    private Long insumoId;
    private InsumoDTO insumo;
    private Long campanaId;
}
