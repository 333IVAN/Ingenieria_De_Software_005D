package com.kiwi.service_direccion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwi.service_direccion.model.Direccion;
import com.kiwi.service_direccion.service.DireccionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/direcciones")
public class DireccionController {
    @Autowired
    private DireccionService diSer;

    @PostMapping
    public ResponseEntity<Direccion> crear(@Valid @RequestBody Direccion dir){
        return ResponseEntity.ok(diSer.crear(dir));
    }

    @GetMapping
    public List<Direccion> listar(){
        return diSer.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> buscarPorId(@PathVariable Long id){
        return diSer.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        diSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
