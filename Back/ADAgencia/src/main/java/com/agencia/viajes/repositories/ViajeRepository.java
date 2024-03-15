package com.agencia.viajes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agencia.viajes.models.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
}
