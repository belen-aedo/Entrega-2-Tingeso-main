package com.example.tarifa_service.Repository;


import com.example.tarifa_service.Entity.TarifaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifaRepository extends JpaRepository<TarifaEntity, Long> {
    TarifaEntity findByTipoAndTiempoMax(String tipo, int tiempomax);
    TarifaEntity findByTipoAndNumeroVueltas(String tipo, int numeroVueltas);
    List<TarifaEntity> findByTipo(String tipo);
}
