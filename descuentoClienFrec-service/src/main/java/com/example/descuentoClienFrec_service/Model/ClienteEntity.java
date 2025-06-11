package com.example.descuentoClienFrec_service.Model;

import java.time.LocalDate;

public class ClienteEntity {

    private Long id;
    private String rut;
    private String nombre;
    private String emailContacto;
    private String numeroMovil;
    private LocalDate nacimiento;

    // Getter y setter conservados tal como pediste
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Métodos con nombres modificados para email, móvil y nacimiento

    public String obtenerEmail() {
        return emailContacto;
    }

    public void asignarEmail(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public String obtenerMovil() {
        return numeroMovil;
    }

    public void establecerMovil(String numeroMovil) {
        this.numeroMovil = numeroMovil;
    }

    public LocalDate obtenerNacimiento() {
        return nacimiento;
    }

    public void establecerNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }
}
