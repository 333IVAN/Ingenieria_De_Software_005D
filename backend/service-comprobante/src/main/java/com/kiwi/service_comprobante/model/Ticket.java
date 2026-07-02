package com.kiwi.service_comprobante.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="ticket")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private Integer estado;

    @Transient
    private UsuarioDTO donante;
    @NotNull
    @Column(nullable = false)
    private Long donanteId;

    @Transient
    private UsuarioDTO voluntario;
    @NotNull
    @Column(nullable = false)
    private Long voluntarioId;
    
    @Transient
    private DonacionDTO donacion;
    @NotNull
    @Column(nullable = false)
    private Long donacionId;
}
