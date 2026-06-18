package com.kiwi.service_mascota.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwi.service_mascota.model.Adopcion;
import com.kiwi.service_mascota.service.AdopcionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/adopciones")
public class AdopcionController {
    @Autowired
    private AdopcionService adSer;

    @PostMapping
    public ResponseEntity<Adopcion> crear(@Valid @RequestBody Adopcion ad){
        return ResponseEntity.ok(adSer.crear(ad));
    }

    @GetMapping
    public List<Adopcion> listar(){
        return adSer.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adopcion> buscarPorId(@PathVariable Long id){
        return adSer.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adopcion> actualizar(@PathVariable Long id, @Valid @RequestBody Adopcion ad) {
        return adSer.actualizar(id, ad)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        adSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/adoptante/{adId}")
    public List<Adopcion> listarPorAdoptante(@PathVariable Long adId){
        return adSer.listarPorAdoptante(adId);
    }

    @GetMapping("/voluntario/{voId}")
    public List<Adopcion> listarPorVoluntario(@PathVariable Long voId){
        return adSer.listarPorVoluntario(voId);
    }
}
