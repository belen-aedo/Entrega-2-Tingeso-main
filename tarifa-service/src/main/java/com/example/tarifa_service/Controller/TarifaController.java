package com.example.tarifa_service.Controller;

import com.example.tarifa_service.Entity.TarifaEntity;
import com.example.tarifa_service.Service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarifa")
public class TarifaController {
    @Autowired
    private TarifaService tarifaServicio;

    //Crear Tarifa
    @PostMapping("/nuevaTarifa")
    public ResponseEntity<TarifaEntity> nuevaTarifa(@RequestBody TarifaEntity tarifa) {
        return ResponseEntity.ok(tarifaServicio.NuevaTarifa(tarifa.getNumeroVueltas(), tarifa.getTiempoMax(), tarifa.getPrecio(), tarifa.getDuracionReserva(), tarifa.getTipo()));
    }

    @GetMapping("/obtenerTarifas")
    public ResponseEntity<List<TarifaEntity>> obtenerTarifas() {
        return ResponseEntity.ok(tarifaServicio.ObtenerTodasLasTarifas());
    }

    @PutMapping("/modificarTarifa")
    public void modificarTarifa(@RequestBody TarifaEntity tarifa) {
        tarifaServicio.modificarTarifa(tarifa.getId(), tarifa.getNumeroVueltas(), tarifa.getTiempoMax(), tarifa.getPrecio(), tarifa.getDuracionReserva(), tarifa.getTipo());
    }

    @GetMapping("/obtenerTarifa")
    public ResponseEntity<TarifaEntity> obtenerTarifa(@RequestParam Long id) {
        return ResponseEntity.ok(tarifaServicio.obtenerTarifa(id));
    }

    @GetMapping("/obtenerTarifaPorTipo")
    public ResponseEntity<List<TarifaEntity>> obtenerTarifaPorTipo(@RequestParam String tipo) {
        return ResponseEntity.ok(tarifaServicio.obtenerTarifaPorTipo(tipo));
    }
}
