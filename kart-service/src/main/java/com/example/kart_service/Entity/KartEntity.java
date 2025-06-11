package com.example.kart_service.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class KartEntity {
    //Atributos
    @Id //Identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String codigo;
    @Getter
    private String modelo;
    @Setter
    @Getter
    private String estado;

    //Constructores
    public KartEntity() {
    }

    public KartEntity(String codigo, String modelo, String estado) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.estado = estado;
    }

}
