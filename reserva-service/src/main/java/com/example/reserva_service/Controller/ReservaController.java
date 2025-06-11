package com.example.reserva_service.Controller;

import com.example.reserva_service.Entity.ReservaEntity;
import com.example.reserva_service.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    ReservaService reservaServicio;

    @PostMapping("/hacerReserva")
    public ResponseEntity<ReservaEntity> hacerReserva(@RequestBody ReservaEntity reserva) {
        return ResponseEntity.ok(reservaServicio.realizarReserva(
                reserva.getRutCliente(),
                reserva.getNombreCliente(),
                reserva.getCorreoCliente(),
                reserva.getFechaReserva(),
                reserva.getHoraInicio(),
                reserva.getRutsAmigos(),
                reserva.getNombres(),
                reserva.getNumVueltas(),
                reserva.getTiempoMax(),
                reserva.getPrecioTarifa(),
                reserva.getDuracionReserva(),
                reserva.getTipoTarifa()
        ));
    }

    @GetMapping("/obtenerReservas")
    public ResponseEntity<List<ReservaEntity>> obtenerReservas() {
        return ResponseEntity.ok(reservaServicio.ObtenerReservas());
    }

    @GetMapping("/obtenerReservasEntreFechas")
    public ResponseEntity<List<ReservaEntity>> obtenerReservasEntreFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        return ResponseEntity.ok(reservaServicio.obtenerReservasEntreFechas(fechaInicio, fechaFin));
    }

}
