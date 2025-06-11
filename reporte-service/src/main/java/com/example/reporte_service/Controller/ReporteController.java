package com.example.reporte_service.Controller;

import com.example.reporte_service.Entity.ReporteEntity;
import com.example.reporte_service.Service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reporte")
public class ReporteController {
    @Autowired
    ReporteService reporteServicio;

    @PostMapping("/hacerReporte")
    public ResponseEntity<ReporteEntity> hacerReporte(@RequestBody ReporteEntity reporte) {
        return ResponseEntity.ok(reporteServicio.crearReporte(reporte.getTipo(), reporte.getFechaInicio(), reporte.getFechaFin()));
    }
}
