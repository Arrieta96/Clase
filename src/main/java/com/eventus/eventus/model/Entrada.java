package com.eventus.eventus.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name="Entrada")
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntrada;

    @Column(name="EntradaPrecio",nullable = false)
    private Double EntradaPrecio;

    @Column(name="cantidadEntrada",nullable = false)
    private Integer cantidadEntrada;

    @Column(name="stockEntrada",nullable = true)
    private Integer stockEntrada;

    @Size(min=3,message = "El nombre de la entrada debe tener minimo 3 caracteres")
    @Column(name="nombreEntrada",nullable = false, length = 50)
    private String nombreEntrada;

    @ManyToOne
    @JoinColumn(name="idEvento",nullable = false,foreignKey = @ForeignKey(name="FK_Entrada_Evento"))
    private Evento Evento;



}