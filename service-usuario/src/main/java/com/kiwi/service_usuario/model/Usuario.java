package com.kiwi.service_usuario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(min = 7, max = 8, message = "El número de RUT debe tener entre 7 y 8 caracteres")
    @Column(nullable = false, length = 10, unique = true)
    private String rut;
    
    @NotBlank
    @Size(min = 1, max = 1, message = "El DV debe tener solo un carácter")
    @Column(nullable = false, length = 1)
    private String dv;
    
    @NotBlank
    @Column(nullable = false)
    private String pnombre;
    
    //opcional
    private String snombre;
    
    @NotBlank
    @Column(nullable = false)
    private String appaterno;

    //opcional
    private String apmaterno;

    @NotBlank
    @Size(min = 8, max = 14, message = "El télefono debe tener un mínimo de 8 caracteres")
    @Column(nullable = false, length = 14, unique = true)
    private String telefono;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Email 
    private String correo;

    @NotNull
    @Column(nullable = false)
    private Long direccionId;

    @NotBlank
    @Size(min = 8, max = 60, message = "La contraseña debe tener un mínimo de 8 caracteres")
    @Column(nullable = false)
    private String contrasena;

    
}
