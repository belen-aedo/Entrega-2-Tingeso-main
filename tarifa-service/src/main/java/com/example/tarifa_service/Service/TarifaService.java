package com.example.tarifa_service.Service;

import com.example.tarifa_service.Entity.TarifaEntity;
import com.example.tarifa_service.Repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TarifaService {
    @Autowired
    TarifaRepository tarifaRepositorio;

    // Crear una tarifa
    public TarifaEntity NuevaTarifa(int numeroVueltas, int tiempoMax, Double precio, int duracionReserva, String tipo){

        if(!(Objects.equals(tipo, "normal"))){
            throw new IllegalArgumentException("la tarifa debe ser de tipo dia especial o fin de semana");
        }

        if(tiempoMax < 0){
            throw new IllegalArgumentException(" El tiempo maximo permitido debe ser positivo");
        }

        if(precio < 0){
            throw new IllegalArgumentException(" El precio debe ser positivo");
        }

        if(duracionReserva < tiempoMax){
            throw new IllegalArgumentException("La duracion total de la reserva debe ser mayor que el tiempo máximo permitido");
        }

        TarifaEntity tarifa = new TarifaEntity(numeroVueltas, tiempoMax, precio, duracionReserva, tipo);

        return tarifaRepositorio.save(tarifa);
    }

    public List<TarifaEntity> ObtenerTodasLasTarifas(){
        return tarifaRepositorio.findAll();
    }

    //modificar alguna caracteristica de una tarifa
    public void modificarTarifa(Long id, int nuevasVueltas, int nuevoTiempomax, double nuevoPrecio, int nuevaDuracion, String nuevoTipo){
        TarifaEntity tarifa = tarifaRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarifa no encontrada"));

        if(tarifa == null){
            throw new IllegalArgumentException("La tarifa no existe");
        }

        if(nuevoTiempomax < 0){
            throw new IllegalArgumentException(" El tiempo maximo permitido debe ser positivo");
        }

        if(nuevoPrecio < 0){
            throw new IllegalArgumentException(" El precio debe ser positivo");
        }

        if(nuevaDuracion < nuevoTiempomax){
            throw new IllegalArgumentException("La duración total de la reserva debe ser mayor que el tiempo máximo permitido");
        }

        tarifa.setNumeroVueltas(nuevasVueltas);
        tarifa.setTiempoMax(nuevoTiempomax);
        tarifa.setPrecio(nuevoPrecio);
        tarifa.setDuracionReserva(nuevaDuracion);
        tarifa.setTipo(nuevoTipo);

        tarifaRepositorio.save(tarifa);
        return;
    }

    public TarifaEntity obtenerTarifa(Long id){
        return tarifaRepositorio.findById(id).get();
    }

    public List<TarifaEntity> obtenerTarifaPorTipo(String tipo) {
        return tarifaRepositorio.findByTipo(tipo);
    }
}