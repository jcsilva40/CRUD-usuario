package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Feriado;
import com.stfn2.ggas.domain.dtos.filter.FeriadoFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FeriadoRepository extends BaseRepository<Feriado, FeriadoFilterDTO> {
    Optional<Feriado> findByDataFeriadoAndDescricao(LocalDate dataFeriado, String descricao);

    @Query("""
            SELECT COUNT(f) FROM Feriado f 
            WHERE f.descricao = :descricao 
            AND f.dataFeriado >= :dataFeriado 
            AND MONTH(f.dataFeriado) = MONTH(:dataFeriado) 
            AND DAY(f.dataFeriado) = DAY(:dataFeriado)
            """)
    Integer countAnosFeriadoAfterData(String descricao, LocalDate dataFeriado);

    @Query("""
            SELECT count(f.id) 
            FROM Feriado f 
            WHERE f.habilitado = true 
            AND f.dataFeriado = :data 
            AND f.feriadoMunicipal = false
            """)
    Long isDiaUtil(@Param("data") LocalDate data);

}