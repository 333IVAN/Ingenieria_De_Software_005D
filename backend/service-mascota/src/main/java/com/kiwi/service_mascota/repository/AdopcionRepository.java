package com.kiwi.service_mascota.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kiwi.service_mascota.model.Adopcion;

@Repository
public interface AdopcionRepository extends JpaRepository<Adopcion, Long>{
    @Query("""
            SELECT a FROM Adopcion a
            WHERE a.voluntarioId = :voluntarioId
            """)
    List<Adopcion> listarPorVoluntario(Long voluntarioId);

    @Query("""
            SELECT a FROM Adopcion a
            WHERE a.adoptanteId = :adoptanteId
            """)
    List<Adopcion> listarPorAdoptante(Long adoptanteId);
}
