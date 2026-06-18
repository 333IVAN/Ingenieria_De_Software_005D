package com.kiwi.service_donacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kiwi.service_donacion.model.Donacion;

@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Long> {
    boolean existsById(Long id);
    
    @Query("""
            SELECT d FROM Donacion d
            WHERE d.usuarioId = :usuarioId
            """)
    List<Donacion> listarPorUsuario(Long usuarioId);

    @Query("""
            SELECT d FROM Donacion d
            WHERE d.insumoId = :insumoId
            """)
    List<Donacion> listarPorInsumo(Long insumoId);

    @Query("""
        SELECT d FROM Donacion d
        WHERE d.campanaId = :campanaId
                """)
        List<Donacion> listarPorCampana(Long campanaId);
}
