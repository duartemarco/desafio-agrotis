package com.agrotis.agrotis_backend.repository;

import com.agrotis.agrotis_backend.application.dto.LaboratorioDTO;
import com.agrotis.agrotis_backend.domain.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {

    @Query("SELECT new com.agrotis.agrotis_backend.application.dto.LaboratorioDTO(l.id, UPPER(l.nome), COUNT(p)) " +
            "FROM Laboratorio l " +
            "LEFT JOIN l.pessoas p " +
            "WHERE (:nome IS NULL OR l.nome LIKE %:nome%) " +
            "AND (:dataInicial IS NULL OR p.dataInicial >= :dataInicial) " +
            "AND (:dataFinal IS NULL OR p.dataFinal <= :dataFinal) " +
            "AND (:observacoes IS NULL OR LOWER(p.observacoes) LIKE LOWER(CONCAT('%', :observacoes, '%'))) " +
            "GROUP BY l.id " +
            "HAVING COUNT(p) >= :quantidadePessoas " +
            "ORDER BY COUNT(p) DESC, MIN(p.dataInicial) ASC")
    List<LaboratorioDTO> filtrarLaboratorios(
            @Param("nome") String nome,
            @Param("dataInicial") LocalDateTime dataInicial,
            @Param("dataFinal") LocalDateTime dataFinal,
            @Param("observacoes") String observacoes,
            @Param("quantidadePessoas") Integer quantidadePessoas
            );

}
