package com.kiwi.service_usuario.controller;

import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwi.service_usuario.model.Usuario;
import com.kiwi.service_usuario.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios de KiwiAyuda")
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usSer;

    @PostMapping
    public ResponseEntity<Usuario> crear(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestBody Usuario us) {

        return ResponseEntity.ok(usSer.crear(us, token));
    }

    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public List<Usuario> listar(){
        return usSer.listarTodos();
    }

    @Operation(summary = "Buscar usuario por id")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return usSer.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Buscar usuario por RUT")
    @GetMapping("/rut/{rut}")
    public ResponseEntity<Usuario> buscarPorRut(@PathVariable String rut){
        return usSer.buscarPorRut(rut)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VOLUNTARIOC')")
    @Operation(summary = "Actualizar usuario")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @Valid @RequestBody Usuario us) {
        return usSer.actualizar(id, us)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_VOLUNTARIOC')")
    @Operation(summary = "Borrar usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
