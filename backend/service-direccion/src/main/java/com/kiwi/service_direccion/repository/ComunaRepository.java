package com.kiwi.service_direccion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiwi.service_direccion.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Long>{
    boolean existsById(Long id);  
    Optional<Comuna> findByNombre(String nombre);  
}
