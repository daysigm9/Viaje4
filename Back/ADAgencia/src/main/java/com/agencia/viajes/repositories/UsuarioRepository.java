package com.agencia.viajes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agencia.viajes.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   
}
