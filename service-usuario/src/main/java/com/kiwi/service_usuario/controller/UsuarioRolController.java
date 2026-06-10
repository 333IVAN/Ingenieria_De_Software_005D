package com.kiwi.service_usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiwi.service_usuario.model.Usuario;
import com.kiwi.service_usuario.model.UsuarioRol;
import com.kiwi.service_usuario.service.UsuarioRolService;

@RestController
@RequestMapping("/usuariorol")
public class UsuarioRolController {
    @Autowired
    private UsuarioRolService urSer;

    @PostMapping
    public UsuarioRol asignar(@RequestParam Long usuarioId, @RequestParam Long rolId){
        return urSer.asignarRol(usuarioId, rolId);
    }

    @GetMapping
    public List<UsuarioRol> listar(){
        return urSer.listarTodo();
    }

    @GetMapping("/rol/{rolId}")
    public List<Usuario> listarPorRol(@PathVariable Long rolId){
        return urSer.lisarPorRol(rolId);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        urSer.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
