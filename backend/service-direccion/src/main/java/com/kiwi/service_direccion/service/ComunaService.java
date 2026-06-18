package com.kiwi.service_direccion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwi.service_direccion.model.Comuna;
import com.kiwi.service_direccion.repository.ComunaRepository;

@Service
public class ComunaService {
    @Autowired
    private ComunaRepository coRep;

    public List<Comuna> listarTodas(){
        return coRep.findAll();
    }

    public Comuna crear(Comuna com) {
    if (com.getId() != null && coRep.existsById(com.getId())) {
        throw new RuntimeException("La comuna ya existe");
    }
    return coRep.save(com);
    }

    public Optional<Comuna> buscarPorId(Long id){
        return coRep.findById(id);
    }

    public void eliminar(Long id) {
        coRep.deleteById(id);
    }
}
