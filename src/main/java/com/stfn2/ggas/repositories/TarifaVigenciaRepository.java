package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.TarifaVigencia;
import com.stfn2.ggas.domain.dtos.filter.TarifaVigenciaFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaVigenciaRepository extends BaseRepository<TarifaVigencia, TarifaVigenciaFilterDTO> {

   @Query("""
            SELECT
                max(tavi)
            FROM
                TarifaVigencia tavi            
            WHERE 1=1
                AND (tavi.tarifa.id = :tarifaId)
            ORDER BY
                tavi.dataVigencia desc
            """)
   TarifaVigencia obterTarifaVigenciaAtualporTarifa(@Param("tarifaId")Long tarifaId);
}
