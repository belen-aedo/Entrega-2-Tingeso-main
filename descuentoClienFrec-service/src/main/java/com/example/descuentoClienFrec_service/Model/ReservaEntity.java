package com.example.descuentoClienFrec_service.Model;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaEntity {

    private String rutCliente;
    private String nombreCliente;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int tiempoTotal;

    @ElementCollection
    private List<String> rutsAmigos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaReserva;
    private String tipoTarifa;
    private LocalTime horaReserva;
    private int numVueltas;
    private int tiempoMax;
    private int cantidadPersonas;
    private String correoCliente;
    private int duracionReserva;

    @ElementCollection
    private List<String> nombres;
    private double precioTarifa;

    @ElementCollection
    private List<String> tiposDescuentos;
    @ElementCollection
    private List<Double> descuentos;
    @ElementCollection
    private List<Double> montosConDescuento;

    private double montoTotal;
    private double valorIva;
    private double montoTotalConIva;

    // Getters y setters omitidos por brevedad
}
