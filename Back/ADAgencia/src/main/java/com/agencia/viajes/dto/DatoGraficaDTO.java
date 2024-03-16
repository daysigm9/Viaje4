package com.agencia.viajes.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatoGraficaDTO {
    private String viaje;
    private Integer cantidad;
}
