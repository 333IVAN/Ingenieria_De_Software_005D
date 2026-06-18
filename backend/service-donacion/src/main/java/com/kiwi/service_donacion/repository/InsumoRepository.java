package com.kiwi.service_donacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiwi.service_donacion.model.Insumo;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long>{
    boolean existsById(Long id);
    boolean existsByDescripcion(String descripcion);
    Optional<Insumo> findByDescripcion(String descripcion);   
    Optional<Insumo> findByUnidad(String unidad); 
}
