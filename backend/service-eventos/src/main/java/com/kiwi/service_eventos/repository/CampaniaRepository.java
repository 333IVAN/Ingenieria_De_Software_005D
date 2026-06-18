package com.kiwi.service_eventos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kiwi.service_eventos.model.Campania;

@Repository
public interface CampaniaRepository extends JpaRepository<Campania, Long> {
    @Query("""
            SELECT c FROM Campania c
            WHERE c.idAdministrador = :idAdministrador
            """)
    List<Campania> listarPorAdministrador(Long idAdministrador);

    @Query("""
            SELECT c FROM Campania c
            WHERE YEAR(c.fechaIni) = :anio
            """)
    List<Campania> listarPorAnio(int anio);

    @Query("""
            SELECT c FROM Campania c
            WHERE MONTH(c.fechaIni) = :mes
            """)
    List<Campania> listarPorMes(int mes);
}
