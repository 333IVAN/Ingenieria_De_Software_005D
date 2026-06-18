package com.kiwi.service_eventos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kiwi.service_eventos.model.Campania;
import com.kiwi.service_eventos.model.UsuarioDTO;
import com.kiwi.service_eventos.repository.CampaniaRepository;

@Service
public class CampaniaService {
    @Autowired
    private CampaniaRepository caRep;
    @Autowired
    private WebClient web;

    public List<Campania> listarTodos(){
        return caRep.findAll();
    }

    public Optional<Campania> buscarPorId(Long id){
        return caRep.findById(id);
    }

    public Campania crear(Campania nueva) {
    UsuarioDTO admin = web.get()
            .uri("/usuarios/{id}", nueva.getIdAdministrador())
            .retrieve()
            .bodyToMono(UsuarioDTO.class)
            .block();
    nueva.setAdministrador(admin);

    UsuarioDTO coord1 = web.get()
            .uri("/usuarios/{id}", nueva.getIdCoordinador1())
            .retrieve()
            .bodyToMono(UsuarioDTO.class)
            .block();
    nueva.setCoordinador(coord1);

    if (nueva.getIdCoordinador2() != null) {
        UsuarioDTO coord2 = web.get()
                .uri("/usuarios/{id}", nueva.getIdCoordinador2())
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();
        nueva.setCoordinador2(coord2);
    }

    if (nueva.getIdCoordinador3() != null) {
        UsuarioDTO coord3 = web.get()
                .uri("/usuarios/{id}", nueva.getIdCoordinador3())
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();
        nueva.setCoordinador3(coord3);
    }

        return caRep.save(nueva); 
    }

    public void eliminar(Long id) {
        caRep.deleteById(id);
    }

    public List<Campania> listarPorAdministrador(Long aId) {
        return caRep.listarPorAdministrador(aId); 
    }

    public List<Campania> listarPorAnio(int anio) {
        return caRep.listarPorAnio(anio);
    }

    public List<Campania> listarPorMes(int mes) {
        return caRep.listarPorMes(mes);
    }

    public Optional<Campania> actualizar(Long id, Campania datosActualizados) {
    Optional<Campania> campaniaOpcional = caRep.findById(id);
    
    if (campaniaOpcional.isPresent()) {
        Campania campaniaExistente = campaniaOpcional.get();
        try {
            UsuarioDTO admin = web.get()
                    .uri("/usuarios/{id}", datosActualizados.getIdAdministrador())
                    .retrieve()
                    .bodyToMono(UsuarioDTO.class)
                    .block();
            campaniaExistente.setAdministrador(admin);
            campaniaExistente.setIdAdministrador(datosActualizados.getIdAdministrador());
            UsuarioDTO coord1 = web.get()
                    .uri("/usuarios/{id}", datosActualizados.getIdCoordinador1())
                    .retrieve()
                    .bodyToMono(UsuarioDTO.class)
                    .block();
            campaniaExistente.setCoordinador(coord1);
            campaniaExistente.setIdCoordinador1(datosActualizados.getIdCoordinador1());

        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener la información de los encargados");
        }
        if (datosActualizados.getIdCoordinador2() != null) {
            try {
                UsuarioDTO coord2 = web.get()
                        .uri("/usuarios/{id}", datosActualizados.getIdCoordinador2())
                        .retrieve()
                        .bodyToMono(UsuarioDTO.class)
                        .block();
                campaniaExistente.setCoordinador2(coord2);
                campaniaExistente.setIdCoordinador2(datosActualizados.getIdCoordinador2());
            } catch (Exception e) {
                throw new RuntimeException("El coordinador 2 ingresado no existe");
            }
        } else {
            campaniaExistente.setCoordinador2(null);
            campaniaExistente.setIdCoordinador2(null);
        }
        if (datosActualizados.getIdCoordinador3() != null) {
            try {
                UsuarioDTO coord3 = web.get()
                        .uri("/usuarios/{id}", datosActualizados.getIdCoordinador3())
                        .retrieve()
                        .bodyToMono(UsuarioDTO.class)
                        .block();
                campaniaExistente.setCoordinador3(coord3);
                campaniaExistente.setIdCoordinador3(datosActualizados.getIdCoordinador3());
            } catch (Exception e) {
                throw new RuntimeException("El coordinador 3 ingresado no existe");
            }
        } else {
            campaniaExistente.setCoordinador3(null);
            campaniaExistente.setIdCoordinador3(null);
        }

        campaniaExistente.setDescripcion(datosActualizados.getDescripcion());
        campaniaExistente.setFechaIni(datosActualizados.getFechaIni());
        campaniaExistente.setFechaFin(datosActualizados.getFechaFin());
        return Optional.of(caRep.save(campaniaExistente));
        } else {
            return Optional.empty();
        }
    }
}
