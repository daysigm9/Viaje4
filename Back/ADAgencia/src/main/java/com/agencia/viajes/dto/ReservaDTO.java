package com.agencia.viajes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {
    private Integer reservaId;
    private Integer usuarioId;
    private Integer subEscalaOrigen;
    private Integer subEscalaDestino;
    private Integer cantidadAsientos;
    private Double precio;
    private Integer viajeId;

}