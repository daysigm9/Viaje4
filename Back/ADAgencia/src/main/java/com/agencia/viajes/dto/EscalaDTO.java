package com.agencia.viajes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscalaDTO {

    private Integer escalaId;
    private String origen;
    private String destino;
    private Double precio;
    private Integer viajeId;

}
