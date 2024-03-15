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
@Table(name = "Autobus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autobus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autobusid")
    private Integer autobusId;

    @Column(name = "cantidadasientos", nullable = false)
    private Integer cantidadAsientos;

    @Column(name = "Disponibles", nullable = false)
    private Integer disponibles;

    @Column(name = "Ocupados", nullable = false)
    private Integer ocupados;

}
