package com.agencia.viajes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutobusDTO {

    private Integer autobusId;
    private Integer cantidadAsientos;
    private Integer disponibles;
    private Integer ocupados;

}