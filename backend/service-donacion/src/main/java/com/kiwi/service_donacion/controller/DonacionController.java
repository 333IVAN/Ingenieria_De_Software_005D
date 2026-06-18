package com.kiwi.service_donacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwi.service_donacion.model.Donacion;
import com.kiwi.service_donacion.service.DonacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/donaciones")
public class DonacionController {
    @Autowired
    private DonacionService doSer;

    @PostMapping
    public ResponseEntity<Donacion> crear(@Valid @RequestBody Donacion don){
        return ResponseEntity.ok(doSer.crear(don));
    }

    @GetMapping
    public List<Donacion> listar(){
        return doSer.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donacion> buscarPorId(@PathVariable Long id){
        return doSer.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

   @PutMapping("/{id}")
    public ResponseEntity<Donacion> actualizar(@PathVariable Long id, @Valid @RequestBody Donacion datos) {
        return doSer.actualizar(id, datos)
                .map(donacionActualizada -> ResponseEntity.ok().body(donacionActualizada))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        doSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/donante/{donId}")
    public List<Donacion> listarPorDonante(@PathVariable Long donId){
        return doSer.listarPorUsuario(donId);
    }

    @GetMapping("/insumo/{inId}")
    public List<Donacion> listarPorInsumo(@PathVariable Long inId){
        return doSer.listarPorInsumo(inId);
    }

    @GetMapping("/campana/{cId}")
    public List<Donacion> listarPorCampana(@PathVariable Long cId){
        return doSer.listarPorCampana(cId);
    }
}
