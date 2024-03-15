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

//Esto indica que es una entidad
@Entity
@Table(name = "Usuario")
//Getter Setter ToString  y HashCode
@Data
//Constructor sin parametros
@NoArgsConstructor
//Constructor con parametros
@AllArgsConstructor
public class Usuario {
	//Es el Pk
    @Id
    //Este indica que es identity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Usuarioid")
    private Long usuarioId;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefono;
}
