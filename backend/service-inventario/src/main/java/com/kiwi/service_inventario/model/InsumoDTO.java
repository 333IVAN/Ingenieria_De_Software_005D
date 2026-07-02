package com.kiwi.service_inventario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsumoDTO {
    private Long id;
    private String descripcion;
    private String unidad;
}
