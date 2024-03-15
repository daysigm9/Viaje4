package com.agencia.viajes.models;


import java.time.LocalDateTime;

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
@Table(name = "Viaje")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viajeid")
    private Integer viajeId;

    @Column(name = "fechahora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "Precio", nullable = false)
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "autobusid", nullable = false)
    private Autobus autobus;

}