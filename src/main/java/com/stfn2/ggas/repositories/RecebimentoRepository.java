package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Recebimento;
import com.stfn2.ggas.domain.dtos.filter.RecebimentoFilterDTO;
import com.stfn2.ggas.domain.enumTypes.RecebimentoSituacaoEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RecebimentoRepository extends BaseRepository<Recebimento, RecebimentoFilterDTO> {

   @Query("""
            SELECT
                 sum(recebimento.valorRecebimento)
            FROM
                Recebimento recebimento            
            WHERE 1=1
            AND (recebimento.faturaGeral.faturaAtual.id = :faturaId )
            AND (recebimento.recebimentoSituacaoEnum != :pRecebimentoSituacao)
            """)
   BigDecimal obterValorRecebimentoPelaFatura(@Param("faturaId") Long faturaId,
                                             @Param("pRecebimentoSituacao") RecebimentoSituacaoEnum pRecebimentoSituacao);

   @Query("""
            SELECT
                 recebimento
            FROM
                Recebimento recebimento            
            WHERE 1=1
               AND (recebimento.faturaGeral.faturaAtual.id = :faturaId )
               AND (recebimento.recebimentoSituacaoEnum in :pRecebimentoSituacao)
            ORDER BY recebimento.dataRecebimento asc
            """)
   List<Recebimento> obterRecebimentoClassificadoPelaFatura(@Param("faturaId") Long faturaId,
                                                            @Param("pRecebimentoSituacao") List<RecebimentoSituacaoEnum> pRecebimentoSituacao);

    @Query("""
            SELECT
                 recebimento
            FROM
                Recebimento recebimento
            WHERE 1=1
               AND (recebimento.faturaGeral.id = :faturaGeralId )
            ORDER BY recebimento.dataRecebimento asc
            """)
    List<Recebimento> obterRecebimentoPorFaturaGeral(@Param("faturaGeralId") Long id);
}
