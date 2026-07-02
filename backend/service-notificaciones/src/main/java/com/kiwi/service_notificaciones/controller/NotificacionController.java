package com.kiwi.service_notificaciones.controller;

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

import com.kiwi.service_notificaciones.model.Notificacion;
import com.kiwi.service_notificaciones.service.NotificacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    @Autowired
    private NotificacionService noSer;

    @PostMapping
    public ResponseEntity<Notificacion> crear(@Valid @RequestBody Notificacion not){
        return ResponseEntity.ok(noSer.crear(not));
    }

    @GetMapping
    public List<Notificacion> listar(){
        return noSer.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> buscarPorId(@PathVariable Long id){
        return noSer.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizar(@PathVariable Long id, @Valid @RequestBody Notificacion not) {
        return noSer.actualizar(id, not)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/leida")
    public ResponseEntity<Notificacion> marcarLeida(@PathVariable Long id) {
        return noSer.marcarLeida(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        noSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Notificacion> listarPorUsuario(@PathVariable Long usuarioId){
        return noSer.listarPorUsuario(usuarioId);
    }

    @GetMapping("/estado/{leida}")
    public List<Notificacion> listarPorEstado(@PathVariable Boolean leida){
        return noSer.listarPorEstado(leida);
    }
}
