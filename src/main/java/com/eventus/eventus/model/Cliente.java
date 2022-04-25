package com.eventus.eventus.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name="Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Size(min=3,message = "El nombre debe tener minimo 3 caracteres y maximo 50")
    @Column(name="nombreCliente",nullable = false,length = 50)
    private String nombreCliente;

    @Size(min=3,message = "El apellido debe tener minimo 3 caracteres y maximo 50")
    @Column(name="apellidoCliente",nullable = false, length = 50)
    private String apellidoCliente;

    @Size(min=9,message = "El celular debe tener minimo 9 caracteres")
    @Column(name="celularCliente",length = 9)
    private String celularCliente;

    @Size(min=3,message = "El correo debe tener minimo 3 caracteres y maximo 100")
    @Column(name="correoCliente",nullable = false, length = 100)
    private String correoCliente;

    @Column(name="fechaNacimientoCliente")
    private String fechaNacimientoCliente;

    @Size(min=3,message = "La dirección debe tener minimo 3 caracteres y maximo 100")
    @Column(name="direccionCliente",length = 100)
    private String direccionCliente;

    @Size(min=6,message = "La contraseña debe tener minimo 6 caracteres y maximo 50")
    @Column(name="contraseñaCliente",length = 300,unique = true)
    private String contraseñaCliente;

    @Column(name="tipoDocumento",nullable = false)
    private String tipoDocumento;

    @Size(min=8,message = "El numero de documento debe tener minimo 8 caracteres y maximo 12")
    @Column(name="numeroDocumento",nullable = false,length = 12)
    private String numeroDocumento;

    @ManyToOne
    @JoinColumn(name="idUsuario",foreignKey = @ForeignKey(name="FK_Cliente_Usuario"))
    private Usuario Usuario;


}