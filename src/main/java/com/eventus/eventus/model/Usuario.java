package com.eventus.eventus.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name="Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Size(min=3,message = "El nombre debe tener minimo 3 caracteres")
    @Column(name="nombreUsuario",nullable = false,length = 50)
    private String nombreUsuario;

    @Size(min=3,message = "La decripcion debe tener minimo 3 caracteres")
    @Column(name="descripcionUsuario",length = 100)
    private String descripcionUsuario;

}