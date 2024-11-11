package com.stfn2.ggas.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.ConsumoHistorico;
import com.stfn2.ggas.domain.dtos.filter.ConsumoHistoricoFilterDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface ConsumoHistoricoRepository extends BaseRepository<ConsumoHistorico, ConsumoHistoricoFilterDTO> {
    @Query("""
            SELECT
                cohi
            FROM
                ConsumoHistorico cohi
            INNER JOIN
               cohi.historicoAtual mh
            WHERE
                cohi.pontoConsumo.id = :idPontoConsumo
                AND mh.dataLeituraInformada = :dataLeitura
                AND cohi.indicadorConsumoCiclo = false
                AND cohi.habilitado = true
            ORDER BY
                mh.dataLeituraInformada DESC
            """)
    ConsumoHistorico buscaPorPontoConsumoEdataLeituraHistorico(@Param("idPontoConsumo") Long idPontoConsumo,
                                                               @Param("dataLeitura") LocalDate dataLeitura);
}