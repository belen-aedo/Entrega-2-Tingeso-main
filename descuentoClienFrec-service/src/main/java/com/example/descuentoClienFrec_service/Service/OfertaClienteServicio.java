package com.example.descuentoClienFrec_service.Service;

import com.example.descuentoClienFrec_service.Entity.DescuentoClienteFrecuente;
import com.example.descuentoClienFrec_service.Model.ReservaEntity;
import com.example.descuentoClienFrec_service.Model.ClienteEntity;
import com.example.descuentoClienFrec_service.Repository.OfertaClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfertaClienteServicio {

    @Autowired
    private OfertaClienteRepositorio ofertaClienteRepositorio;

    @Autowired
    private RestTemplate restTemplate;

    public List<ClienteEntity> obtenerClientesPorRut(List<String> rutsIntegrantes) {
        List<ClienteEntity> clientes = new ArrayList<>();
        for (String rut : rutsIntegrantes) {
            ClienteEntity cliente = restTemplate.getForObject("http://usuario-service/cliente/buscarPorRut/" + rut, ClienteEntity.class);
            clientes.add(cliente);
        }
        return clientes;
    }

    public List<ReservaEntity> obtenerReservasEntre(LocalDate inicioMes, LocalDate finMes) {
        String url = "http://reserva-service/reserva/obtenerReservasEntreFechas?fechaInicio={inicioMes}&fechaFin={finMes}";

        ResponseEntity<List<ReservaEntity>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReservaEntity>>() {},
                inicioMes.toString(),
                finMes.toString()
        );

        return response.getBody();
    }

    public List<Double> obtenerDescuentosFrecuencia(Long idReserva, List<String> rutsIntegrantes, LocalDate fechaReserva) {
        List<Double> descuentos = new ArrayList<>();

        for (String rut : rutsIntegrantes) {
            int frecuencia = calcularFrecuencia(fechaReserva, rut);
            if (frecuencia >= 2 && frecuencia <= 4) {
                descuentos.add(0.10);
            } else if (frecuencia >= 5 && frecuencia <= 6) {
                descuentos.add(0.20);
            } else if (frecuencia > 6) {
                descuentos.add(0.30);
            } else {
                descuentos.add(0.00);
            }
        }

        DescuentoClienteFrecuente entity = new DescuentoClienteFrecuente(idReserva, descuentos);

        // ofertaClienteRepositorio.save(entity);

        return descuentos;
    }

    public int calcularFrecuencia(LocalDate fecha, String rutIntegrante) {
        if (rutIntegrante == null || rutIntegrante.isEmpty()) {
            throw new IllegalArgumentException("El rut no puede estar vacío");
        }

        YearMonth mes = YearMonth.from(fecha);
        LocalDate inicioMes = mes.atDay(1);
        LocalDate finMes = mes.atEndOfMonth();

        int frecuencia = 0;
        List<ReservaEntity> reservas = obtenerReservasEntre(inicioMes, finMes);



        return frecuencia;
    }
}
