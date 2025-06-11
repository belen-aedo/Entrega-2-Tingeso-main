package com.example.reserva_service.Repository;

import com.example.reserva_service.Entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
    List<ReservaEntity> findByFechaReserva(LocalDate fecha);

    List<ReservaEntity> findByFechaReservaBetween(LocalDate startDate, LocalDate endDate);

}
