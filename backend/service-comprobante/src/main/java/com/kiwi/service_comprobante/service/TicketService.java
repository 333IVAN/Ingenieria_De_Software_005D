package com.kiwi.service_comprobante.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kiwi.service_comprobante.model.DonacionDTO;
import com.kiwi.service_comprobante.model.Ticket;
import com.kiwi.service_comprobante.model.UsuarioDTO;
import com.kiwi.service_comprobante.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository tiRep;
    @Autowired
    @Qualifier("usuarioWebClient")
    private WebClient web;
    @Autowired
    @Qualifier("donacionWebClient")
    private WebClient webc;

    public List<Ticket> listarTodos(){
        return tiRep.findAll();
    }

    public Ticket crear(Ticket nueva) {
        UsuarioDTO user = web.get()
            .uri("/{id}", nueva.getDonanteId())
            .retrieve()
            .bodyToMono(UsuarioDTO.class)
            .block(); 
        nueva.setDonante(user);

        UsuarioDTO user2 = web.get()
            .uri("/{id}", nueva.getVoluntarioId())
            .retrieve()
            .bodyToMono(UsuarioDTO.class)
            .block(); 
        nueva.setVoluntario(user2);

        DonacionDTO dona = webc.get()
            .uri("/{id}", nueva.getDonacionId())
            .retrieve()
            .bodyToMono(DonacionDTO.class)
            .block(); 
        nueva.setDonacion(dona);
        return tiRep.save(nueva);
    }

    public Optional<Ticket> buscarPorId(Long id){
        return tiRep.findById(id);
    }

    public Optional<Ticket> actualizar(Long id, Ticket datosActualizados) {
    Optional<Ticket> ticketOpcional = tiRep.findById(id);
    
    if (ticketOpcional.isPresent()) {
        Ticket ticketExistente = ticketOpcional.get();
        try {
            UsuarioDTO user = web.get()
                .uri("/{id}", datosActualizados.getDonanteId())
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();
            ticketExistente.setDonante(user);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener la información del usuario");
        }

        try {
            UsuarioDTO user2 = web.get()
                .uri("/{id}", datosActualizados.getVoluntarioId())
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();
            ticketExistente.setVoluntario(user2);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener la información del usuario");
        }

        try {
            DonacionDTO dona = webc.get()
                .uri("/{id}", datosActualizados.getDonacionId())
                .retrieve()
                .bodyToMono(DonacionDTO.class)
                .block();
            ticketExistente.setDonacion(dona);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener la informacion de la donacion");
        }

        ticketExistente.setEstado(datosActualizados.getEstado());
        ticketExistente.setDonanteId(datosActualizados.getDonanteId());
        ticketExistente.setVoluntarioId(datosActualizados.getVoluntarioId());
        ticketExistente.setDonacionId(datosActualizados.getDonacionId());
        
            return Optional.of(tiRep.save(ticketExistente));
        } else {
            return Optional.empty();
        }
    }

    public void eliminar(Long id) {
        tiRep.deleteById(id);
    }


    public List<Ticket> listarPorDonante(Long uId) {
        return tiRep.listarPorDonante(uId); 
    }

    public List<Ticket> listarPorVoluntario(Long uId) {
        return tiRep.listarPorVoluntario(uId); 
    }

}
