package com.kiwi.service_usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwi.service_usuario.model.Usuario;
import com.kiwi.service_usuario.model.UsuarioRol;
import com.kiwi.service_usuario.repository.UsuarioRolRepository;

@Service
public class UsuarioRolService {
    @Autowired
    private UsuarioRolRepository urRep;
    
    public List<UsuarioRol> listarTodo() {
        return urRep.findAll();
    }

    public List<Usuario> lisarPorRol(Long rolId){
        return urRep.listarPorRol(rolId);
    }

    public UsuarioRol asignarRol(Long usuarioId, Long rolId){
        if (urRep.existsByUsuarioIdAndRolId(usuarioId, rolId)){
            throw new RuntimeException("El usuario ya tiene este rol");
        }
    UsuarioRol ur = new UsuarioRol();
    ur.setUsuarioId(usuarioId);
    ur.setRolId(rolId);
    
    return urRep.save(ur);
    }

    public void eliminar(Long id) {
        urRep.deleteById(id);
    }
    
}
