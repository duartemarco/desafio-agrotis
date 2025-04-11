package com.agrotis.agrotis_backend.repository;

import com.agrotis.agrotis_backend.application.dto.LaboratorioResponseDTO;
import com.agrotis.agrotis_backend.domain.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {

    @Query("""
                SELECT new com.agrotis.agrotis_backend.application.dto.LaboratorioResponseDTO(
                    l.id,
                    UPPER(l.nome),
                    COUNT(p.id)
                )
                FROM Laboratorio l
                JOIN l.pessoas p
                WHERE (CAST(:dataInicialComeco AS timestamp) IS NULL OR p.dataInicial >= :dataInicialComeco)
                AND (CAST(:dataInicialFim AS timestamp) IS NULL OR p.dataInicial <= :dataInicialFim)
                AND (CAST(:dataFinalComeco AS timestamp) IS NULL OR p.dataFinal >= :dataFinalComeco)
                AND (CAST(:dataFinalFim AS timestamp) IS NULL OR p.dataFinal <= :dataFinalFim)
                AND (COALESCE(:observacoes, '') = '' OR LOWER(p.observacoes) LIKE LOWER(CONCAT('%', :observacoes, '%')))
                GROUP BY l.id, l.nome
                HAVING COUNT(p.id) >= :quantidadePessoas
                ORDER BY COUNT(p.id) DESC, MIN(p.dataInicial) ASC
            """)
    List<LaboratorioResponseDTO> filtrarLaboratorios(@Param("dataInicialComeco") LocalDateTime dataInicialComeco, @Param("dataInicialFim") LocalDateTime dataInicialFim, @Param("dataFinalComeco") LocalDateTime dataFinalComeco, @Param("dataFinalFim") LocalDateTime dataFinalFim, @Param("observacoes") String observacoes, @Param("quantidadePessoas") Integer quantidadePessoas);

}
