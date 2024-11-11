package com.stfn2.ggas.repositories;

import com.stfn2.ggas.domain.enumTypes.CreditoDebitoSituacaoEnum;
import com.stfn2.ggas.domain.projection.CreditoDebitoARealizarBasicProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.CreditoDebitoARealizar;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoARealizarFilterDTO;


@Repository
public interface CreditoDebitoARealizarRepository extends BaseRepository<CreditoDebitoARealizar, CreditoDebitoARealizarFilterDTO> {

    @Query("""
            SELECT
                crdr.id as id,
                pocn.descricao as pontoConsumoDescricao,
                pocn.cil as pontoConsumoCil,
                crdr.anoMesFaturamento as anoMesFaturamento,
                crdr.ciclo as ciclo,
                crdn.descricao as credDebNegComplemento,
                rubr.descricao as tipoCobrancaRubrDesc,
                crdr.valor as valor,
                crdr.creditoDebitoSituacao as creditoDebitoSituacao,
                peri.descricao as periodicidade,
                CASE
                        WHEN crdr.creditoDebitoSituacao = 1 AND crdn.quantidadePrestacaoCobrada IS NOT NULL AND crdn.quantidadePrestacaoCobrada > 0 THEN true
                        WHEN crdr.creditoDebitoSituacao = 1 AND crdn.quantidadePrestacaoCobrada IS NULL OR crdn.quantidadePrestacaoCobrada <= 0 THEN false
                        ELSE true
                    END as executada
            FROM
                CreditoDebitoARealizar crdr
            JOIN
                crdr.creditoDebitoNegociado crdn
            JOIN
               crdn.pontoConsumo pocn 
            JOIN
               crdn.rubrica rubr 
            JOIN
                pocn.segmento segm
            JOIN
                segm.periodicidade peri
            WHERE 1=1
            AND (:cil IS NULL OR pocn.cil = :cil)
            AND (:anoMesFaturamento IS NULL OR crdr.anoMesFaturamento = :anoMesFaturamento)
            AND (:ciclo IS NULL OR crdr.ciclo =:ciclo)
            AND (:creditoDebitoSituacao IS NULL OR crdr.creditoDebitoSituacao =:creditoDebitoSituacao)
            AND (:credDebNegComplemento IS NULL OR crdn.descricao =: credDebNegComplemento )
            AND (:isExecutada IS NULL OR
            (CASE
            WHEN crdr.creditoDebitoSituacao = 1 AND (crdn.quantidadePrestacaoCobrada IS NOT NULL AND crdn.quantidadePrestacaoCobrada > 0) THEN true
            ELSE false
            END) = :isExecutada)
            ORDER BY
                crdr.id desc
            """)
    Page<CreditoDebitoARealizarBasicProjection> findAll(
            @Param("cil") String pontoConsumoCil,
            @Param("anoMesFaturamento") Integer anoMesFaturamento,
            @Param("ciclo") Integer ciclo,
            @Param ("creditoDebitoSituacao") CreditoDebitoSituacaoEnum creditoDebitoSituacaoEnum,
            @Param ("credDebNegComplemento") String credDebNegComplemento,
            @Param ("isExecutada") Boolean executada,
            Pageable pageable
    );

}