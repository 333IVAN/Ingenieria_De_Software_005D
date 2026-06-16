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

import com.kiwi.service_direccion.model.Comuna;
import com.kiwi.service_direccion.service.ComunaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comunas")
public class ComunaController {
    @Autowired
    private ComunaService coSer;

    @PostMapping
    public ResponseEntity<Comuna> crear(@Valid @RequestBody Comuna com){
        return ResponseEntity.ok(coSer.crear(com));
    }

    @GetMapping
    public List<Comuna> listar(){
        return coSer.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comuna> buscarPorId(@PathVariable Long id){
        return coSer.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        coSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
