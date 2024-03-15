package com.agencia.viajes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agencia.viajes.models.Escala;

@Repository
public interface EscalaRepository extends JpaRepository<Escala, Integer> {
}
