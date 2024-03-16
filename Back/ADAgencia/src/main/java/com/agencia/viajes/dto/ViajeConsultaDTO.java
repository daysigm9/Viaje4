package com.agencia.viajes.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViajeConsultaDTO {
    private Integer idViaje;
    private String origen;
    private String destino;
    private Double importe;
    private LocalDateTime fechaSalida;
    private Integer pasajeros;
}