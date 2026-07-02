package com.kiwi.service_sucursal.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwi.service_sucursal.model.Sucursal;
import com.kiwi.service_sucursal.service.SucursalService;

import jakarta.validation.Valid;

@RestController
@Tag(name = "Sucursales", description = "Operaciones relacionadas con sucursales de KiwiAyuda")
@RequestMapping("/sucursales")
public class SucursalController {
    @Autowired
    private SucursalService suSer;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VOLUNTARIOC')")
    @PostMapping
    public ResponseEntity<Sucursal> crear(@Valid @RequestBody Sucursal suc){
        return ResponseEntity.ok(suSer.crear(suc));
    }

    @GetMapping
    public List<Sucursal> listar(){
        return suSer.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> buscarPorId(@PathVariable Long id){
        return suSer.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VOLUNTARIOC')")
    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizar(@PathVariable Long id, @Valid @RequestBody Sucursal suc) {
        return suSer.actualizar(id, suc)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VOLUNTARIOC')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        suSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/direccion/{direccionId}")
    public List<Sucursal> listarPorDireccion(@PathVariable Long direccionId){
        return suSer.listarPorDireccion(direccionId);
    }
}
