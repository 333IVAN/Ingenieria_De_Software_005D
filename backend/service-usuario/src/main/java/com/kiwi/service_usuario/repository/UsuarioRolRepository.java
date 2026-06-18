package com.kiwi.service_usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kiwi.service_usuario.model.Rol;
import com.kiwi.service_usuario.model.Usuario;
import com.kiwi.service_usuario.model.UsuarioRol;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long>{
    boolean existsByUsuarioIdAndRolId(Long usuarioId, Long rolId);

    @Query("""
            SELECT u FROM Usuario u
            JOIN UsuarioRol ur ON u.id = ur.usuarioId
            WHERE ur.rolId = :rolId
            """)
        List<Usuario> listarPorRol(Long rolId);

    @Query("""
            SELECT r FROM Rol r
            JOIN UsuarioRol ur ON r.id = ur.rolId
                WHERE ur.usuarioId = :usuarioId
            """)
        List<Rol> obtenerRolesDeUsuario(Long usuarioId);

}
