package com.kiwi.service_sucursal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DireccionDTO {
    private Long id;
    private String calle;
    private int numeracion;
    private ComunaDTO comuna;
}
