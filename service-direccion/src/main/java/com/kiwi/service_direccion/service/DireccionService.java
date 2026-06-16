package com.kiwi.service_direccion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwi.service_direccion.model.Comuna;
import com.kiwi.service_direccion.model.Direccion;
import com.kiwi.service_direccion.repository.ComunaRepository;
import com.kiwi.service_direccion.repository.DireccionRepository;

@Service
public class DireccionService {
    @Autowired
    private DireccionRepository diRep;
    @Autowired
    private ComunaRepository coRep;

    public List<Direccion> listarTodas(){
        return diRep.findAll();
    }

    public Direccion crear(Direccion dir) {
    Comuna comunaExistente = coRep.findById(dir.getComuna().getId())
        .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
        dir.setComuna(comunaExistente); 
        return diRep.save(dir);
    }

    public Optional<Direccion> buscarPorId(Long id){
        return diRep.findById(id);
    }

    public void eliminar(Long id) {
        diRep.deleteById(id);
    }
}
