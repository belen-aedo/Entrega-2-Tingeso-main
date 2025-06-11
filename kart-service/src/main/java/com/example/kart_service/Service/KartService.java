package com.example.kart_service.Service;

import com.example.kart_service.Entity.KartEntity;
import com.example.kart_service.Repository.KartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KartService {
    @Autowired
    private KartRepository kartRepositorio;

    @Autowired
    public KartService(KartRepository kartRepositorio) {
        this.kartRepositorio = kartRepositorio;
    }

    //Nuevo kart
    public KartEntity nuevoKart(String codigo, String modelo, String estado) {
        KartEntity newKart = new KartEntity(codigo, modelo, estado);
        KartEntity existente = kartRepositorio.findByCodigo(newKart.getCodigo());
        if (existente != null) {
            return null;
        }
        return kartRepositorio.save(newKart);
    }

    // Obtener karts a partir de su estado (disponible, mantenimiento y ocupado)
    public List<KartEntity> obtenerKartsEstado(String estado) {
        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("\"El estado no puede ser nulo o vacío.");
        }
        return kartRepositorio.findByEstado(estado);
    }

    // Cambiar el estado de un kart
    public void cambiarEstado(String codigo, String newEstado) {
        KartEntity kart = kartRepositorio.findByCodigo(codigo);

        if (kart == null) {
            throw new IllegalArgumentException("Kart no encontrado con código: " + codigo);
        }

        kart.setEstado(newEstado);
        kartRepositorio.save(kart);
        return;
    }

}
