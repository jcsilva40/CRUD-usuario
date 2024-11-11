package com.stfn2.ggas.repositories;

import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.CreditoDebitoNegociado;
import com.stfn2.ggas.domain.dtos.filter.CreditoDebitoNegociadoFilterDTO;

import java.math.BigDecimal;

@Repository
public interface CreditoDebitoNegociadoRepository extends BaseRepository<CreditoDebitoNegociado, CreditoDebitoNegociadoFilterDTO> {

   @Query("""
        SELECT 
            sum(fait.valorTotal)
        FROM FaturaItem fait
        WHERE 1=1
           AND (fait.creditoDebitoARealizar.id =:idCredito)
           AND (fait.fatura.creditoDebitoSituacao.id !=:idSituacaoCancelada)
           AND (fait.fatura.id =:idFatura)
        """)
   BigDecimal obterValorCobradoCredito(@Param("idCredito") Long idCredito, @Param("idFatura")Long idFatura, @Param("idSituacaoCancelada")Long idSituacaoCancelada);

   @Query("""
        SELECT 
            count(fait)
        FROM FaturaItem fait
        WHERE 1=1
           AND (fait.habilitado is true)
           AND (fait.creditoDebitoARealizar.id =:idCredito)
           AND (fait.fatura.creditoDebitoSituacao.id !=:idSituacaoCancelada)
        """)
   Integer consultarQuantidadeFaturaItemPorCreditoDebito(@Param("idCredito") Long idCredito, @Param("idSituacaoCancelada")Long idSituacaoCancelada);
}