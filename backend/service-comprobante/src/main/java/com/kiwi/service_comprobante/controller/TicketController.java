package com.kiwi.service_comprobante.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwi.service_comprobante.model.Ticket;
import com.kiwi.service_comprobante.service.TicketService;

import jakarta.validation.Valid;

@RestController
@Tag(name = "Comprobantes", description = "Operaciones relacionadas con comprobantes de KiwiAyuda")
@RequestMapping("/tickets")
public class TicketController {
     @Autowired
    private TicketService tiSer;

    @PostMapping
    public ResponseEntity<Ticket> crear(@Valid @RequestBody Ticket tic){
        return ResponseEntity.ok(tiSer.crear(tic));
    }

    @GetMapping
    public List<Ticket> listar(){
        return tiSer.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> buscarPorId(@PathVariable Long id){
        return tiSer.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VOLUNTARIOC')")
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> actualizar(@PathVariable Long id, @Valid @RequestBody Ticket datos) {
        return tiSer.actualizar(id, datos)
                .map(ticketActualizado -> ResponseEntity.ok().body(ticketActualizado))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VOLUNTARIOC')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tiSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/donante/{donId}")
    public List<Ticket> listarPorDonante(@PathVariable Long donId){
        return tiSer.listarPorDonante(donId);
    }

     @GetMapping("/voluntario/{volId}")
    public List<Ticket> listarPorVoluntario(@PathVariable Long volId){
        return tiSer.listarPorVoluntario(volId);
    }
}
