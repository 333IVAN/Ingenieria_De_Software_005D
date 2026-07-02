package com.kiwi.service_inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwi.service_inventario.model.Inventario;
import com.kiwi.service_inventario.repository.InventarioRepository;

@Service
public class InventarioService {
    @Autowired
    private InventarioRepository inRep;

    public List<Inventario> listarTodos(){
        return inRep.findAll();
    }

    public Inventario crear(Inventario inv) {
        return inRep.save(inv);
    }

    public Optional<Inventario> buscarPorId(Long id){
        return inRep.findById(id);
    }

    public Optional<Inventario> buscarPorInsumo(Long insumoId){
        return inRep.findByInsumoId(insumoId);
    }

    public Optional<Inventario> actualizar(Long id, Inventario datosActualizados) {
        Optional<Inventario> inventarioOpcional = inRep.findById(id);

        if (inventarioOpcional.isPresent()) {
            Inventario inventarioExistente = inventarioOpcional.get();
            inventarioExistente.setInsumoId(datosActualizados.getInsumoId());
            inventarioExistente.setDescripcion(datosActualizados.getDescripcion());
            inventarioExistente.setUnidad(datosActualizados.getUnidad());
            inventarioExistente.setStock(datosActualizados.getStock());
            return Optional.of(inRep.save(inventarioExistente));
        } else {
            return Optional.empty();
        }
    }

    public void eliminar(Long id) {
        inRep.deleteById(id);
    }
}
