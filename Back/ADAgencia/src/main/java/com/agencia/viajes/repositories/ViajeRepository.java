package com.agencia.viajes.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agencia.viajes.dto.ViajeConsultaDTO;
import com.agencia.viajes.dto.ViajeReporte;
import com.agencia.viajes.dto.ViajeReporteProjection;
import com.agencia.viajes.models.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
    @Query(value="SELECT " +
            "V.ViajeId AS idViaje, " +
       	    "E.Origen as origen, " + 
    	    "E.Destino as destino, " + 
            "V.Precio AS Importe, " +
    	    "V.FechaHora AS FechaSalida, " + 
            "COUNT(R.ReservaId) AS Pasajeros " +
            "FROM " +
            "Viaje V " +
            "JOIN " +
            "Escala E ON V.IdRuta = E.IdRuta " +
            "LEFT JOIN " +
            "Reserva R ON V.ViajeId = R.ViajeId " +
            "GROUP BY " +
            "V.ViajeId, E.Origen, E.Destino, V.Precio, V.FechaHora", nativeQuery=true)
    List<Map<String, Object>> getDataAsMap();    
}
