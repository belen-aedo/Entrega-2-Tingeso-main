package com.example.kart_service.Controller;


import com.example.kart_service.Entity.KartEntity;
import com.example.kart_service.Service.KartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kart")
public class KartControlador {
    @Autowired
    private KartService kartServicio;

    //Crear nuevo kart
    @PostMapping("/nuevoKart")
    public ResponseEntity<KartEntity> nuevoKart(@RequestBody KartEntity kart) {
        return ResponseEntity.ok(kartServicio.nuevoKart(kart.getCodigo(), kart.getModelo(), kart.getEstado()));
    }

    // Obtener karts a partir de su estado (disponible, mantenimiento y ocupado)
    @GetMapping("/getKartsEstado")
    public ResponseEntity<List<KartEntity>> getKartsEstado(@RequestParam String estado) {
        return ResponseEntity.ok(kartServicio.obtenerKartsEstado(estado));
    }

    // Cambiar el estado de un kart
    @PutMapping("/cambiarEstado")
    public void cambiarEstado(@RequestParam String codigo, @RequestParam String newEstado) {
        kartServicio.cambiarEstado(codigo, newEstado);
        return;
    }


}
