package com.kiwi.service_sucursal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kiwi.service_sucursal.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    @Query("""
            SELECT s FROM Sucursal s
            WHERE s.direccionId = :direccionId
            """)
    List<Sucursal> listarPorDireccion(Long direccionId);
}
