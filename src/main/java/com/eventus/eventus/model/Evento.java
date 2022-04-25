package com.eventus.eventus.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name="Evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    @Size(min=3,message = "El nombre debe tener minimo 3 caracteres y maximo 100")
    @Column(name="nombreEvento",nullable = false,length = 100)
    private String nombreEvento;

    @Column(name="fechaEvento")
    private String fechaEvento;
    
    @Column(name="horaEvento",length = 10)
    private String horaEvento;

    @Size(min=3,message = "La decripcion debe tener minimo 3 caracteres")
    @Column(name="descripcionEvento",length = 200)
    private String descripcionEvento;

    @Size(min=3,message = "La decripcion debe tener minimo 3 caracteres")
    @Column(name="informaciónEvento",length = 200)
    private String informaciónEvento;

    @Column(name="camposEventos",length = 300)
    private String camposEventos;

    @Column(name="portadaEvento",length = 300)
    private String portadaEvento;

    @Size(min=3,message = "Minimo 3 y maximo 300 caracteres")
    @Column(name="estadoEvento",length = 20)
    private String estadoEvento="Activo";

    @ManyToOne
    @JoinColumn(name="idCliente",nullable = false,foreignKey = @ForeignKey(name="FK_Evento_Cliente"))
    private Cliente cliente ;


}