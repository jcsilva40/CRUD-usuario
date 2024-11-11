package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ContaBancaria;
import com.stfn2.ggas.domain.dtos.filter.ContaBancariaFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ContaBancariaRepository extends BaseRepository<ContaBancaria, ContaBancariaFilterDTO> {

    @Query("""
            SELECT 
                coba
            FROM
                ContaBancaria coba
            JOIN
                coba.agencia agen            
            WHERE 
                coba.id = (
                    SELECT MIN(contaBancaria.id)
                    FROM ContaBancaria contaBancaria
                    WHERE contaBancaria.agencia.id = :agenciaId
                )
            """)
    ContaBancaria getContaBancariaPorAgenciaId(@Param("agenciaId") Long agenciaId);
}