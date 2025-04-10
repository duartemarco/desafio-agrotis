package com.agrotis.agrotis_backend.repository;

import com.agrotis.agrotis_backend.domain.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {

}
