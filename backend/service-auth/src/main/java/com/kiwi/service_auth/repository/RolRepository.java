package com.kiwi.service_auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiwi.service_auth.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}