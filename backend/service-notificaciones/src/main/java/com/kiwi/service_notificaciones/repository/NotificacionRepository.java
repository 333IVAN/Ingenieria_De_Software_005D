package com.kiwi.service_notificaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kiwi.service_notificaciones.model.Notificacion;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    @Query("""
            SELECT n FROM Notificacion n
            WHERE n.usuarioId = :usuarioId
            """)
    List<Notificacion> listarPorUsuario(Long usuarioId);

    @Query("""
            SELECT n FROM Notificacion n
            WHERE n.leida = :leida
            """)
    List<Notificacion> listarPorEstado(Boolean leida);
}
