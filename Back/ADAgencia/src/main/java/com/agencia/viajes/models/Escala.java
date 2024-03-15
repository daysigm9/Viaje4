package com.agencia.viajes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(name = "origen", nullable = false, length = 50)
    private String origen;

    @Column(name = "destino", nullable = false, length = 50)
    private String destino;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "viajeid", nullable = false)
    private Viaje viaje;

}
