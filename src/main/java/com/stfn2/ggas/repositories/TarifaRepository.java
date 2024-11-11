package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.abstractClasses.combo.ComboDTO;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Tarifa;
import com.stfn2.ggas.domain.TarifaVigencia;
import com.stfn2.ggas.domain.dtos.filter.TarifaFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarifaRepository extends BaseRepository<Tarifa, TarifaFilterDTO> {
    @Query("""
            SELECT
                new com.stfn2.ggas.core.abstractClasses.combo.ComboDTO(
                    tari.id ,
                    tari.descricao 
                )
            FROM
                Tarifa tari            
            WHERE 1=1
                AND (tari.segmento.id = (SELECT pocn.segmento.id FROM PontoConsumo pocn WHERE pocn.id = :pontoConsumoId))
            ORDER BY
                tari.id
            """)
    List<ComboDTO> findTarifaPorPontoConsumo(@Param("pontoConsumoId") Long pontoConsumoId);

    @Query("""
            SELECT
                tv
            FROM
                TarifaVigencia tv            
            WHERE 1=1
                AND (tv.habilitado = true)
                AND (tv.tarifa.id = :tarifaId)
                AND (tv.dataVigencia = (
                    select max(tavi.dataVigencia) 
                    from TarifaVigencia tavi
                    WHERE 1=1
                    AND (tavi.habilitado = true)
                    AND (tavi.tarifa.id = :tarifaId)
                    AND (tavi.dataVigencia < :dataVigencia)
                    ))
            """)
   TarifaVigencia consultarDadosTarifaVigenciaAnteriorMaisRecente(@Param("tarifaId")Long tarifaId, @Param("dataVigencia")LocalDate dataVigencia);
}
