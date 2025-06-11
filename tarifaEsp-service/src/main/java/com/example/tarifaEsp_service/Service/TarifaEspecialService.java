package com.example.tarifaEsp_service.Service;

import com.example.tarifaEsp_service.Entity.TarifaEspecialEntity;
import com.example.tarifaEsp_service.Model.Cliente;
import com.example.tarifaEsp_service.Repository.TarifaEspecialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TarifaEspecialService {
    @Autowired
    TarifaEspecialRepository tarifaEspecialRepositorio;

    @Autowired
    private RestTemplate restTemplate;

    public TarifaEspecialEntity NuevaTarifa(int numeroVueltas, int tiempoMax, Double precio, int duracionReserva, String tipo){

        if(!(Objects.equals(tipo, "dia especial") || Objects.equals(tipo, "fin de semana"))){
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

        TarifaEspecialEntity tarifa = new TarifaEspecialEntity(numeroVueltas, tiempoMax, precio, duracionReserva, tipo);

        return tarifaEspecialRepositorio.save(tarifa);
    }

    //modificar alguna caracteristica de una tarifa
    public void modificarTarifa(Long id, int nuevasVueltas, int nuevoTiempomax, double nuevoPrecio, int nuevaDuracion, String nuevoTipo){
        TarifaEspecialEntity tarifa = tarifaEspecialRepositorio.findById(id)
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

        tarifaEspecialRepositorio.save(tarifa);
        return;
    }

    public List<TarifaEspecialEntity> ObtenerTodasLasTarifas(){
        return tarifaEspecialRepositorio.findAll();
    }

    public TarifaEspecialEntity obtenerTarifa(Long id){

        return tarifaEspecialRepositorio.findById(id).get();
    }

    public List<Double> obtenerDescuentoCumpleanios(List<String> rutsIntegrantes, LocalDate fechaReserva) {
        List<Cliente> usuarios = obtenerUsuariosPorRut(rutsIntegrantes);
        List<Double> descuentos = new ArrayList<>();
        int contador = 0;

        for (int i = 0; i < rutsIntegrantes.size(); i++) {
            if (i >= usuarios.size() || usuarios.get(i) == null || usuarios.get(i).getFechaNacimiento() == null) {
                descuentos.add(0.0);
                continue;
            }

            LocalDate fechaNacimiento = usuarios.get(i).getFechaNacimiento();
            boolean esCumpleanos = fechaNacimiento.getMonth() == fechaReserva.getMonth() &&
                    fechaNacimiento.getDayOfMonth() == fechaReserva.getDayOfMonth();

            if (esCumpleanos && contador < 2) {
                descuentos.add(0.5);
                contador++;
            } else {
                descuentos.add(0.0);
            }
        }

        return descuentos;
    }

    public List<Cliente> obtenerUsuariosPorRut(List<String> rutsIntegrantes) {
        Cliente usuario;
        List<Cliente> usuarios = new ArrayList<>();
        for (int i = 0; i < rutsIntegrantes.size(); i++) {
            usuario = restTemplate.getForObject("http://usuario-service/usuario/buscarPorRut/"+rutsIntegrantes.get(i), Cliente.class);
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public List<TarifaEspecialEntity> obtenerTarifaPorTipo(String tipo) {
        return tarifaEspecialRepositorio.findByTipo(tipo);
    }

    public String obtenerTipoTarifaPorFecha(LocalDate fecha) {
        if (fecha.getDayOfWeek().getValue() >= 6) {
            return "fin de semana"; // Fin de semana
        } else if (obtenerFeriados().contains(fecha)) {
            return "dia especial"; // Día especial
        } else {
            return "normal"; // Tarifa normal
        }
    }

    public List<LocalDate> obtenerFeriados() {
        List<LocalDate> feriados = new ArrayList<>();
        feriados.add(LocalDate.of(2024, 1, 1)); // Año Nuevo
        feriados.add(LocalDate.of(2024, 5, 1)); // Día del Trabajador
        feriados.add(LocalDate.of(2024, 10, 31)); // Halloween
        return feriados;
    }
}
