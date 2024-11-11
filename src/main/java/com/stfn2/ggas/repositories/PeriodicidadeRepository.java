package com.stfn2.ggas.repositories;

import com.stfn2.ggas.domain.dtos.filter.PeriodicidadeFilterDTO;
import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Periodicidade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface PeriodicidadeRepository extends BaseRepository<Periodicidade, PeriodicidadeFilterDTO> {

    @Query("""
            SELECT 
                CASE WHEN COUNT(peri) > 0 THEN MIN(peri.id) ELSE null END
            FROM
                PontoConsumo pocn
            JOIN
                pocn.segmento segm
            JOIN
                segm.periodicidade peri
            WHERE
                pocn.id = :pontoConsumoId                
            """)
    Long periodicidadePorPontoConsumo(@Param("pontoConsumoId") Long pontoConsumoId);
}
