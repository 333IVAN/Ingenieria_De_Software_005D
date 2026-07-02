package com.kiwi.service_sucursal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kiwi.service_sucursal.model.DireccionDTO;
import com.kiwi.service_sucursal.model.Sucursal;
import com.kiwi.service_sucursal.repository.SucursalRepository;

@Service
public class SucursalService {
    @Autowired
    private SucursalRepository suRep;
    @Autowired
    private WebClient web;

    public List<Sucursal> listarTodos(){
        return suRep.findAll();
    }

    public Sucursal crear(Sucursal nueva) {
        DireccionDTO direccion = web.get()
            .uri("/{id}", nueva.getDireccionId())
            .retrieve()
            .bodyToMono(DireccionDTO.class)
            .block();
        nueva.setDireccion(direccion);
        return suRep.save(nueva);
    }

    public Optional<Sucursal> buscarPorId(Long id){
        return suRep.findById(id);
    }

    public Optional<Sucursal> actualizar(Long id, Sucursal datosActualizados) {
        Optional<Sucursal> sucursalOpcional = suRep.findById(id);

        if (sucursalOpcional.isPresent()) {
            Sucursal sucursalExistente = sucursalOpcional.get();
            try {
                DireccionDTO direccion = web.get()
                    .uri("/{id}", datosActualizados.getDireccionId())
                    .retrieve()
                    .bodyToMono(DireccionDTO.class)
                    .block();
                sucursalExistente.setDireccion(direccion);
            } catch (Exception e) {
                throw new RuntimeException("La direccion ingresada no existe");
            }

            sucursalExistente.setNombre(datosActualizados.getNombre());
            sucursalExistente.setTelefono(datosActualizados.getTelefono());
            sucursalExistente.setCorreo(datosActualizados.getCorreo());
            sucursalExistente.setDireccionId(datosActualizados.getDireccionId());
            return Optional.of(suRep.save(sucursalExistente));
        } else {
            return Optional.empty();
        }
    }

    public void eliminar(Long id) {
        suRep.deleteById(id);
    }

    public List<Sucursal> listarPorDireccion(Long direccionId){
        return suRep.listarPorDireccion(direccionId);
    }
}
