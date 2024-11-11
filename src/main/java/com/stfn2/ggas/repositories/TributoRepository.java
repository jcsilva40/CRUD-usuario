package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Tributo;
import com.stfn2.ggas.domain.TributoAliquota;
import com.stfn2.ggas.domain.dtos.filter.TributoFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TributoRepository extends BaseRepository<Tributo, TributoFilterDTO> {

   @Query("""
            SELECT 
                tributoAliquota
            FROM
                TributoAliquota tributoAliquota
                inner join fetch tributoAliquota.tributo
            WHERE 1=1
               AND (tributoAliquota.tributo.id = :tributoId)
               AND (tributoAliquota.dataVigencia <= :dataAtual )
            ORDER BY tributoAliquota.dataVigencia desc
            FETCH FIRST 1 ROWS ONLY
            """)
   TributoAliquota obterAliquotaVigente(
           @Param("tributoId") Long tributoId,
           @Param("dataAtual") LocalDate dataAtual
   );
}
