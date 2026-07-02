package com.kiwi.service_comprobante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kiwi.service_comprobante.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository <Ticket, Long>{
    boolean existsById(Long id);
    
    @Query("""
            SELECT d FROM Ticket d
            WHERE d.donanteId = :donanteId
            """)
    List<Ticket> listarPorDonante(Long donanteId);

    @Query("""
            SELECT d FROM Ticket d
            WHERE d.voluntarioId = :voluntarioId
            """)
    List<Ticket> listarPorVoluntario(Long voluntarioId);
}
