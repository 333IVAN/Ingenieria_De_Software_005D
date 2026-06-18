package com.kiwi.service_mascota.controller;

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

import com.kiwi.service_mascota.model.Mascota;
import com.kiwi.service_mascota.service.MascotaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {
    @Autowired
    private MascotaService maSer;

    @PostMapping
    public ResponseEntity<Mascota> crear(@Valid @RequestBody Mascota mas){
        return ResponseEntity.ok(maSer.crear(mas));
    }

    @GetMapping
    public List<Mascota> listar(){
        return maSer.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> buscarPorId(@PathVariable Long id){
        return maSer.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        maSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
