package com.agencia.viajes.dto;

import java.sql.Date;

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
    private String fechaSalida;
    private Integer pasajeros;
}