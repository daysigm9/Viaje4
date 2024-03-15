package com.agencia.viajes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Escala")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Escala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "escalaid")
    private Integer escalaId;

	@Column(name = "idruta")
    private Integer idRuta;

	@Column(name = "subescala")
    private Integer subEscala;

    @Column(name = "Origen", nullable = false, length = 50)
    private String origen;

    @Column(name = "Destino", nullable = false, length = 50)
    private String destino;

    @Column(name = "Precio", nullable = false)
    private Double precio;

}
