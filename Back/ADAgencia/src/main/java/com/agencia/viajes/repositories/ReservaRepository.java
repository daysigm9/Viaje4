package com.agencia.viajes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agencia.viajes.models.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}

