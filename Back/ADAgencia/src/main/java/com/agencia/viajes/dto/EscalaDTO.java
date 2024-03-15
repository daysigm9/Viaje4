package com.agencia.viajes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscalaDTO {

    private Integer escalaId;
	private Integer idRuta;
	private Integer subEscala;
    private String origen;
    private String destino;
    private Double precio;

}