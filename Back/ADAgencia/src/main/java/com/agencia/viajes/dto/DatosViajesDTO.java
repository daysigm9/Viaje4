package com.agencia.viajes.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosViajesDTO {
	private Integer viajeId;
	private LocalDateTime fechaHoraSalida;
	private Integer numeroAutobus;
	private String origen;
	private String destino;
	private Integer idRuta;
	private Integer AsientosLibre;
	private Integer CantidadAsientos;
	private Double Precio;
}
