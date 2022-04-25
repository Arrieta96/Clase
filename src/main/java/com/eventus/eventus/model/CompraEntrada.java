package com.eventus.eventus.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name="CompraEntrada")
public class CompraEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompraEntrada;

    @Column(name="fechaCompra",nullable = false)
    private String fechaCompra;

    @ManyToOne
    @JoinColumn(name="idEntrada",nullable = false,foreignKey = @ForeignKey(name="FK_CompraEntrada_Entrada"))
    private Entrada Entrada;

    @ManyToOne
    @JoinColumn(name="idCliente",nullable = false,foreignKey = @ForeignKey(name="FK_CompraEntrada_Cliente"))
    private Cliente Cliente;


}