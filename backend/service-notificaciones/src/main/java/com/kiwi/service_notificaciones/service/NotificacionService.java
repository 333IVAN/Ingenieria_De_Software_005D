package com.kiwi.service_notificaciones.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwi.service_notificaciones.model.Notificacion;
import com.kiwi.service_notificaciones.repository.NotificacionRepository;

@Service
public class NotificacionService {
    @Autowired
    private NotificacionRepository noRep;

    public List<Notificacion> listarTodos(){
        return noRep.findAll();
    }

    public Notificacion crear(Notificacion not) {
        if (not.getFechaCreacion() == null) {
            not.setFechaCreacion(LocalDate.now());
        }
        if (not.getLeida() == null) {
            not.setLeida(false);
        }
        return noRep.save(not);
    }

    public Optional<Notificacion> buscarPorId(Long id){
        return noRep.findById(id);
    }

    public Optional<Notificacion> actualizar(Long id, Notificacion datosActualizados) {
        Optional<Notificacion> notificacionOpcional = noRep.findById(id);

        if (notificacionOpcional.isPresent()) {
            Notificacion notificacionExistente = notificacionOpcional.get();
            notificacionExistente.setTitulo(datosActualizados.getTitulo());
            notificacionExistente.setMensaje(datosActualizados.getMensaje());
            notificacionExistente.setFechaCreacion(datosActualizados.getFechaCreacion());
            notificacionExistente.setLeida(datosActualizados.getLeida());
            notificacionExistente.setUsuarioId(datosActualizados.getUsuarioId());
            return Optional.of(noRep.save(notificacionExistente));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Notificacion> marcarLeida(Long id) {
        Optional<Notificacion> notificacionOpcional = noRep.findById(id);

        if (notificacionOpcional.isPresent()) {
            Notificacion notificacionExistente = notificacionOpcional.get();
            notificacionExistente.setLeida(true);
            return Optional.of(noRep.save(notificacionExistente));
        } else {
            return Optional.empty();
        }
    }

    public void eliminar(Long id) {
        noRep.deleteById(id);
    }

    public List<Notificacion> listarPorUsuario(Long usuarioId){
        return noRep.listarPorUsuario(usuarioId);
    }

    public List<Notificacion> listarPorEstado(Boolean leida){
        return noRep.listarPorEstado(leida);
    }
}
