package com.agencia.viajes.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViajeDTO {

    private Integer viajeId;
    private LocalDateTime fechaHora;
    private Double precio;
    private Integer usuarioId;
    private Integer autobusId;
    private Integer idRuta;

}