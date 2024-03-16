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
	
	@Query(value="select distinct rut.Origen,rut.Destino "
			+ "from Viaje via "
			+ "inner join ( "
			+ "select IdRuta,max(case when SubEscala=1 then Origen else '' end) Origen "
			+ ",max(case when SubEscala=2 then Destino else '' end) Destino "
			+ "from Escala "
			+ "group by IdRuta "
			+ ") rut "
			+ "on rut.IdRuta=via.IdRuta "
			+ "inner join Autobus aut "
			+ "on aut.AutobusId=via.AutobusId "
			+ "where FechaHora>getdate() ",nativeQuery=true)
    List<Map<String, Object>> getOrigenDestinoAsMap();

	@Query(value="SELECT via.ViajeId, via.FechaHora AS FechaHoraSalida, via.AutobusId AS NumeroAutobus, " +
            "rut.Origen, rut.Destino, rut.IdRuta, aut.CantidadAsientos - ISNULL(aso.AsientosOcupados, 0) AS AsientosLibre,aut.CantidadAsientos, " +
            "via.Precio " +
            "FROM Viaje via " +
            "INNER JOIN ( " +
            "   SELECT IdRuta, " +
            "       MAX(CASE WHEN SubEscala = 1 THEN Origen ELSE '' END) AS Origen, " +
            "       MAX(CASE WHEN SubEscala = 2 THEN Destino ELSE '' END) AS Destino " +
            "   FROM Escala " +
            "   GROUP BY IdRuta " +
            ") rut ON rut.IdRuta = via.IdRuta " +
            "INNER JOIN Autobus aut ON aut.AutobusId = via.AutobusId " +
            "LEFT JOIN ( " +
            "   SELECT ViajeId, COUNT(*) AS AsientosOcupados " +
            "   FROM Reserva res " +
            "   INNER JOIN ReservaAsiento resa ON res.ReservaId = resa.ReservaId " +
            "   GROUP BY ViajeId " +
            ") aso ON aso.ViajeId = via.ViajeId " +
            "WHERE FechaHora > GETDATE() AND Origen = ?1 AND Destino = ?2 and cast(FechaHora as Date)=?3", nativeQuery = true)
     List<Map<String, Object>> getDatosViajesAsMap(String origen,String destino,String Fecha);

	
	@Query(value="SELECT rut.Origen + '-' + rut.Destino AS Viaje, COUNT(*) AS Cantidad " +
            "FROM Viaje via " +
            "INNER JOIN ( " +
            "    SELECT IdRuta, " +
            "           MAX(CASE WHEN SubEscala = 1 THEN Origen ELSE '' END) AS Origen, " +
            "           MAX(CASE WHEN SubEscala = 2 THEN Destino ELSE '' END) AS Destino " +
            "    FROM Escala " +
            "    GROUP BY IdRuta " +
            ") rut ON rut.IdRuta = via.IdRuta " +
            "GROUP BY rut.Origen, rut.Destino",nativeQuery=true)
    List<Map<String, Object>> getDataGraficaAsMap();
	
	
	@Query(value="select Asiento from Reserva res inner join ReservaAsiento resa"
			+" on res.ReservaId=resa.ReservaId where res.ViajeId=?1",nativeQuery=true)
	List<Integer> getAsientosOcupados(int viajeId);
	
	
	@Query(value=" select distinct rut.Origen,rut.Destino "+
			" from Viaje via "+
			" inner join Escala rut "+
			" on rut.IdRuta=via.IdRuta "+
			" inner join Autobus aut "+
			" on aut.AutobusId=via.AutobusId "
,nativeQuery=true)
    List<Map<String, Object>> getOrigenDestinoIntAsMap();
	
	
	@Query(value=
			" select via.ViajeId, via.FechaHora FechaHoraSalida,via.AutobusId NumeroAutobus "+
			" ,rut.Origen,rut.Destino,rut.IdRuta,aut.CantidadAsientos-isnull(aso.AsientosOcupados,0) AsientosLibre,rut.Precio "+
			" from Viaje via  "+
			" inner join Escala rut "+
			" on rut.IdRuta=via.IdRuta "+
			" inner join Autobus aut "+
			" on aut.AutobusId=via.AutobusId "+
			" left join ( "+
			" select ViajeId,count(*) AsientosOcupados "+
			" from Reserva res "+
			" inner join ReservaAsiento resa "+
			" on res.ReservaId=resa.ReservaId "+
			" group by ViajeId "+
			" ) aso "+
			" on aso.ViajeId=via.ViajeId "+
			" WHERE FechaHora > GETDATE() AND Origen = ?1 AND Destino = ?2 and cast(FechaHora as Date)=?3 "
			, nativeQuery = true)
     List<Map<String, Object>> getDatosViajesIntAsMap(String origen,String destino,String Fecha);


	
}
