package com.kiwi.service_usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwi.service_usuario.model.Rol;
import com.kiwi.service_usuario.repository.RolRepository;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRep;

    public List<Rol> listarTodo() {
        return rolRep.findAll();
    }

    public Optional<Rol> buscarPorId(Long Id) {
        return rolRep.findById(Id);
    }

    public Rol guardar(Rol rol) {
        if (rolRep.existsByDescripcion(rol.getDescripcion())) {
            throw new RuntimeException("El rol ya existe");
        }
    return rolRep.save(rol);
    }

    public void eliminar(Long id) {
        rolRep.deleteById(id);
    }

    public Optional<Rol> actualizar(Long id, Rol datosActualizados) {
    Optional<Rol> rolOpcional = rolRep.findById(id);
    if (rolOpcional.isPresent()) {
        Rol rolExistente = rolOpcional.get();
        if (!rolExistente.getDescripcion().equalsIgnoreCase(datosActualizados.getDescripcion()) && 
            rolRep.existsByDescripcion(datosActualizados.getDescripcion())) {
            throw new RuntimeException("Ya existe un rol con esa descripción");
        }
        rolExistente.setDescripcion(datosActualizados.getDescripcion());
        Rol rolGuardado = rolRep.save(rolExistente);
        return Optional.of(rolGuardado);
    } else {
        return Optional.empty();
    }
    }
}
