package com.kiwi.service_inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kiwi.service_inventario.model.MovimientoInventario;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {
    @Query("""
            SELECT m FROM MovimientoInventario m
            WHERE m.inventarioId = :inventarioId
            """)
    List<MovimientoInventario> listarPorInventario(Long inventarioId);

    @Query("""
            SELECT m FROM MovimientoInventario m
            WHERE m.tipo = :tipo
            """)
    List<MovimientoInventario> listarPorTipo(String tipo);

    @Query("""
            SELECT m FROM MovimientoInventario m
            WHERE m.donacionId = :donacionId
            """)
    List<MovimientoInventario> listarPorDonacion(Long donacionId);
}
