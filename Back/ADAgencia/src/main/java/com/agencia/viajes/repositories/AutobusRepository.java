package com.agencia.viajes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agencia.viajes.models.Autobus;

@Repository
public interface AutobusRepository extends JpaRepository<Autobus, Integer> {
	
}
