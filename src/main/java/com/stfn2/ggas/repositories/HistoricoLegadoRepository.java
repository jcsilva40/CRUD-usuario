package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.HistoricoLegado;
import com.stfn2.ggas.domain.dtos.filter.HistoricoLegadoFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoLegadoRepository extends BaseRepository<HistoricoLegado, HistoricoLegadoFilterDTO> {

   @Query("""
        SELECT 
            historico
        FROM HistoricoLegado historico
        WHERE 1=1
           AND (historico.pontoConsumo = :pontoConsumoId)
        """)
   List<HistoricoLegado> listarHistoricoLegado(@Param("pontoConsumoId")String pontoConsumoId);
}