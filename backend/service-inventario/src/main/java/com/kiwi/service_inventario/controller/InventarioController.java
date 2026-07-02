package com.kiwi.service_inventario.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
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

import com.kiwi.service_inventario.model.Inventario;
import com.kiwi.service_inventario.service.InventarioService;

import jakarta.validation.Valid;

@RestController
@Tag(name = "Inventario", description = "Operaciones relacionadas con inventario de KiwiAyuda")
@RequestMapping("/inventarios")
public class InventarioController {
    @Autowired
    private InventarioService inSer;

    @PostMapping
    public ResponseEntity<Inventario> crear(@Valid @RequestBody Inventario inv){
        return ResponseEntity.ok(inSer.crear(inv));
    }

    @GetMapping
    public List<Inventario> listar(){
        return inSer.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscarPorId(@PathVariable Long id){
        return inSer.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/insumo/{insumoId}")
    public ResponseEntity<Inventario> buscarPorInsumo(@PathVariable Long insumoId){
        return inSer.buscarPorInsumo(insumoId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizar(@PathVariable Long id, @Valid @RequestBody Inventario inv) {
        return inSer.actualizar(id, inv)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
