package com.kiwi.service_usuario.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwi.service_usuario.model.Rol;
import com.kiwi.service_usuario.service.RolService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Tag(name = "Roles", description = "Operaciones relacionadas con roles de KiwiAyuda")
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolSer;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VOLUNTARIOC')")
    @PostMapping
    public Rol crear(@RequestBody Rol rol){
        return rolSer.guardar(rol);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VOLUNTARIOC','ROLE_USER')")
    @GetMapping
    public List<Rol> listarTodos(){
        return rolSer.listarTodo();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VOLUNTARIOC','ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Rol> buscarPorId(@PathVariable Long id){
        return rolSer.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VOLUNTARIOC')")
    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizar(@PathVariable Long id, @Valid @RequestBody Rol rol) {
        return rolSer.actualizar(id, rol)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VOLUNTARIOC')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        rolSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    
        
}
