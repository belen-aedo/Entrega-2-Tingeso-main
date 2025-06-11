package com.example.descuentoClienFrec_service.Controller;

import com.example.descuentoClienFrec_service.Service.OfertaClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ofertasCliente")
public class OfertasClienteControlador {

    @Autowired
    private OfertaClienteServicio ofertaClienteServicio;

    @GetMapping("/calcularDescuentos")
    public ResponseEntity<List<Double>> calcularDescuentos(@RequestParam Long idReserva,
                                                           @RequestParam List<String> rutsIntegrantes,
                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaReserva) {
        return ResponseEntity.ok(ofertaClienteServicio.obtenerDescuentosFrecuencia(idReserva, rutsIntegrantes, fechaReserva));
    }
}
