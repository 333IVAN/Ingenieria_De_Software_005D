package com.kiwi.service_usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiwi.service_usuario.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    boolean existsById(Long id);
    boolean existsByRut(String rut);
    Optional<Usuario> findByRut(String rut);     
}
