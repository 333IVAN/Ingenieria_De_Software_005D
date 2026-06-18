package com.kiwi.service_eventos.controller;

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

import com.kiwi.service_eventos.model.Campania;
import com.kiwi.service_eventos.service.CampaniaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/campanias")
public class CampaniaController {
    @Autowired
    private CampaniaService caSer;

    @PostMapping
    public ResponseEntity<Campania> crear(@Valid @RequestBody Campania cam){
        return ResponseEntity.ok(caSer.crear(cam));
    }

    @GetMapping
    public List<Campania> listar(){
        return caSer.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campania> buscarPorId(@PathVariable Long id){
        return caSer.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campania> actualizar(@PathVariable Long id, @Valid @RequestBody Campania cam) {
        return caSer.actualizar(id, cam)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        caSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/anio/{anio}")
    public List<Campania> listarPorAnio(@PathVariable int anio){
        return caSer.listarPorAnio(anio);
    }

    @GetMapping("/mes/{mes}")
    public List<Campania> listarPorMes(@PathVariable int mes){
        return caSer.listarPorMes(mes);
    }

    @GetMapping("/administrador/{aId}")
    public List<Campania> listarPorAdmin(@PathVariable Long aId){
        return caSer.listarPorAdministrador(aId);
    }
}
