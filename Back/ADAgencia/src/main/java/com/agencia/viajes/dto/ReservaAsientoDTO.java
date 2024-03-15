package com.agencia.viajes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaAsientoDTO {
    private Integer idReservaAsientos;
    private Integer reservaId;
    private Integer asiento;
}