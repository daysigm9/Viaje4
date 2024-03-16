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
@Table(name = "reservaasiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaAsiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreservaasientos")
    private Integer idReservaAsientos;

    @Column(nullable = false)
    private Integer asiento;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "reservaid", referencedColumnName = "reservaid")
    private Reserva reserva;

}
