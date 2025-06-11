package com.example.tarifaEsp_service.Repository;

import com.example.tarifaEsp_service.Entity.TarifaEspecialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifaEspecialRepository extends JpaRepository<TarifaEspecialEntity, Long> {
    List<TarifaEspecialEntity> findByTipo(String tipo);
}
