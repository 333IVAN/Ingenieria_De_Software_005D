package com.kiwi.service_donacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwi.service_donacion.model.Insumo;
import com.kiwi.service_donacion.repository.InsumoRepository;

@Service
public class InsumoService {
    @Autowired
    private InsumoRepository inRep;

    public List<Insumo> listarTodos(){
        return inRep.findAll();
    }

    public Insumo crear(Insumo in){
        if(inRep.existsByDescripcion(in.getDescripcion())){
            throw new RuntimeException("El insumo ya existe");
        }
        return inRep.save(in);
    }

    public Optional<Insumo> buscarPorId(Long id){
        return inRep.findById(id);
    }

    public void eliminar(Long id) {
        inRep.deleteById(id);
    }
}
