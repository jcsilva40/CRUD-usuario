package com.stfn2.ggas.repositories;

import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Agencia;
import com.stfn2.ggas.domain.dtos.filter.AgenciaFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AgenciaRepository extends BaseRepository<Agencia, AgenciaFilterDTO> {

    @Query("""
            SELECT 
                agen
            FROM
                Agencia agen
            JOIN
                agen.banco banc            
            WHERE 
                agen.id = (
                    SELECT MIN(agencia.id)
                    FROM Agencia agencia
                    WHERE agencia.banco.id = :bancoId
                )
            """)
    Agencia getAgenciaPorBancoId(@Param("bancoId") Long bancoId);
}