package com.kiwi.service_donacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kiwi.service_donacion.model.CampaniaDTO;
import com.kiwi.service_donacion.model.Donacion;
import com.kiwi.service_donacion.model.Insumo;
import com.kiwi.service_donacion.model.UsuarioDTO;
import com.kiwi.service_donacion.repository.DonacionRepository;
import com.kiwi.service_donacion.repository.InsumoRepository;

@Service
public class DonacionService {
    @Autowired
    private DonacionRepository doRep;
    @Autowired
    private InsumoRepository insRep;
    @Autowired
    @Qualifier("usuarioWebClient")
    private WebClient web;
    @Autowired
    @Qualifier("campaniaWebClient")
    private WebClient webc;

    public List<Donacion> listarTodos(){
        return doRep.findAll();
    }

    public Donacion crear(Donacion nueva) {
    UsuarioDTO user = web.get()
        .uri("/{id}", nueva.getUsuarioId())
        .retrieve()
        .bodyToMono(UsuarioDTO.class)
        .block(); 
    nueva.setUsuario(user);
    Insumo insumoReal = insRep.findById(nueva.getInsumoId())
        .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
    nueva.setInsumo(insumoReal);
    if (nueva.getCampanaId() != null) {
        try {
            CampaniaDTO campanaReal = webc.get()
                .uri("/{id}", nueva.getCampanaId())
                .retrieve()
                .bodyToMono(CampaniaDTO.class)
                .block();
            nueva.setCampana(campanaReal);
        } catch (Exception e) {
            throw new RuntimeException("La campaña no existe");
        }
    }
    return doRep.save(nueva);
    }

    public Optional<Donacion> buscarPorId(Long id){
        return doRep.findById(id);
    }

    public Optional<Donacion> actualizar(Long id, Donacion datosActualizados) {
    Optional<Donacion> donacionOpcional = doRep.findById(id);
    
    if (donacionOpcional.isPresent()) {
        Donacion donacionExistente = donacionOpcional.get();
        try {
            UsuarioDTO user = web.get()
                .uri("/{id}", datosActualizados.getUsuarioId())
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();
            donacionExistente.setUsuario(user);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener la información del usuario");
        }
        Insumo insumoReal = insRep.findById(datosActualizados.getInsumoId())
            .orElseThrow(() -> new RuntimeException("El insumo no existe"));
        donacionExistente.setInsumo(insumoReal);
        if (datosActualizados.getCampanaId() != null) {
            try {
                CampaniaDTO campanaReal = webc.get()
                    .uri("/{id}", datosActualizados.getCampanaId())
                    .retrieve()
                    .bodyToMono(CampaniaDTO.class)
                    .block();
                donacionExistente.setCampana(campanaReal);
            } catch (Exception e) {
                throw new RuntimeException("La campaña vinculada no existe");
            }
        } else {
            donacionExistente.setCampana(null);
        }
        donacionExistente.setCantidad(datosActualizados.getCantidad());
        donacionExistente.setDescripcion(datosActualizados.getDescripcion());
        donacionExistente.setFechadonacion(datosActualizados.getFechadonacion());
        donacionExistente.setUsuarioId(datosActualizados.getUsuarioId());
        donacionExistente.setInsumoId(datosActualizados.getInsumoId());
        donacionExistente.setCampanaId(datosActualizados.getCampanaId()); 

            return Optional.of(doRep.save(donacionExistente));
        } else {
            return Optional.empty();
        }
    }

    public void eliminar(Long id) {
        doRep.deleteById(id);
    }


    public List<Donacion> listarPorUsuario(Long uId) {
        return doRep.listarPorUsuario(uId); 
    }

    public List<Donacion> listarPorInsumo(Long inId) {
        return doRep.listarPorInsumo(inId);
    }
    
    public List<Donacion> listarPorCampana(Long cId) {
        return doRep.listarPorCampana(cId);
    }

}
