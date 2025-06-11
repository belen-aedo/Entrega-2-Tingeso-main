package com.example.rackSemanal_service.Repository;

import com.example.rackSemanal_service.Entity.RackSemanalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RackSemanalRepository extends JpaRepository<RackSemanalEntity, Long> {

}
