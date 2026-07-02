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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwi.service_inventario.model.MovimientoInventario;
import com.kiwi.service_inventario.service.MovimientoInventarioService;

import jakarta.validation.Valid;

@RestController
@Tag(name = "Movimientos de Inventario", description = "Operaciones relacionadas con movimientos de inventario de KiwiAyuda")
@RequestMapping("/movimientos")
public class MovimientoInventarioController {
    @Autowired
    private MovimientoInventarioService moSer;

    @PostMapping
    public ResponseEntity<MovimientoInventario> crear(@Valid @RequestBody MovimientoInventario mov){
        return ResponseEntity.ok(moSer.crear(mov));
    }

    @PostMapping("/donacion/{donacionId}")
    public ResponseEntity<MovimientoInventario> registrarDonacion(@PathVariable Long donacionId){
        return ResponseEntity.ok(moSer.registrarDonacion(donacionId));
    }

    @GetMapping
    public List<MovimientoInventario> listar(){
        return moSer.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventario> buscarPorId(@PathVariable Long id){
        return moSer.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        moSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/inventario/{inventarioId}")
    public List<MovimientoInventario> listarPorInventario(@PathVariable Long inventarioId){
        return moSer.listarPorInventario(inventarioId);
    }

    @GetMapping("/tipo/{tipo}")
    public List<MovimientoInventario> listarPorTipo(@PathVariable String tipo){
        return moSer.listarPorTipo(tipo);
    }

    @GetMapping("/donacion/{donacionId}")
    public List<MovimientoInventario> listarPorDonacion(@PathVariable Long donacionId){
        return moSer.listarPorDonacion(donacionId);
    }
}
