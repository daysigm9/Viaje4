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
@Table(name = "Reserva")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservaid")
    private Integer reservaId;

    @ManyToOne
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuario;

    @Column(name = "subescalaorigen", nullable = false)
    private Integer subEscalaOrigen;

    @Column(name = "subescaladestino", nullable = false)
    private Integer subEscalaDestino;

    @Column(name = "cantidadasientos", nullable = false)
    private Integer cantidadAsientos;

    @Column(name = "Precio", nullable = false)
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "viajeid", nullable = false)
    private Viaje viaje;
}

