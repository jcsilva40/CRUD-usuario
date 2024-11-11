package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.dtos.filter.FaturaFilterDTO;
import com.stfn2.ggas.domain.enumTypes.SituacaoPagamentoEnum;
import com.stfn2.ggas.domain.enumTypes.StatusFaturaEnum;
import com.stfn2.ggas.domain.projection.FaturaBasicProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FaturaRepository extends BaseRepository<Fatura, FaturaFilterDTO> {

   @Query("""
            SELECT 
                fatura.valorTotal as valorTotal,
                fatura.faturaGeral as faturaGeral,
                fatura.dataVencimento as dataVencimento,
                fatura.anoMes as anoMes,
                fatura.consumoHistorico.consumoApurado as consumoHistorico_consumoApurado,
                fatura.consumoHistorico.consumoMedido as consumoHistorico_consumoMedido
            FROM
                Fatura fatura
                inner join fatura.faturaGeral.listaRecebimento                
            WHERE 1=1
               AND (fatura.situacaoPagamento = :situacaoPagamentoId)
               AND (fatura.cliente.id = :clienteId)
               AND (fatura.contratoAtual.id = :contratoId)
               AND (fatura.documentoTipo.id = :tipoDocumentoId)
            
            ORDER BY fatura.anoMes desc, fatura.dataVencimento desc
            """)
   List<Fatura> consultarDadosFaturasQuitadasVersoFatura(
           @Param("clienteId")Long clienteId,
           @Param("contratoId")Long contratoId,
           @Param("tipoDocumentoId")Long tipoDocumentoId,
           @Param("situacaoPagamentoId") SituacaoPagamentoEnum situacaoPagamentoId
   );

   @Query("""
            SELECT 
                faturaTributacao
            FROM
                FaturaTributacao faturaTributacao
                inner join fetch  faturaTributacao.tributo
            WHERE 1=1
               AND (faturaTributacao.fatura.id = :faturaId)
            """)
   List<FaturaTributacao> consultarFaturaTributacaoPorFatura(@Param("faturaId") Long faturaId);

   @Query("""
            SELECT 
                faturaItem
            FROM
                FaturaItem faturaItem
                left join fetch faturaItem.rubrica
                left join fetch faturaItem.rubrica.itemFatura
                left join fetch faturaItem.creditoDebitoARealizar
                left join fetch faturaItem.creditoDebitoARealizar.creditoDebitoNegociado
            WHERE 1=1
               AND (faturaItem.fatura.id = :faturaId)
            ORDER BY faturaItem.valorUnitario desc, faturaItem.rubrica.descricao
            """)
   List<FaturaItem> listarFaturaItemPorFatura(@Param("faturaId") Long faturaId);

   @Query("""
            SELECT 
                faturaItemTributacao
            FROM
                FaturaItemTributacao faturaItemTributacao
                inner join fetch  faturaItemTributacao.tributo
                inner join fetch  faturaItemTributacao.faturaItem
            WHERE 1=1
               AND (faturaItemTributacao.faturaItem.id = :faturaItemId)
            """)
   List<FaturaItemTributacao> listarFaturaItemTributacao(@Param("faturaItemId") Long faturaItemId);

   @Query("""
            SELECT 
                faturaItemImpressao
            FROM
                FaturaItemImpressao faturaItemImpressao
            WHERE 1=1
               AND (faturaItemImpressao.habilitado = true)
               AND (faturaItemImpressao.faturaItem.id = :faturaItemId)
            ORDER BY faturaItemImpressao.desconto   
            """)
   List<FaturaItemImpressao> listarFaturaItemImpressaoPorFaturaItem(@Param("faturaItemId") Long faturaItemId);

   @Query("""
        SELECT  faturaImpressao
             
        FROM 
           FaturaImpressao faturaImpressao
        WHERE 1=1
           AND (faturaImpressao.fatura.id = :faturaId)
        """)
   FaturaImpressao obterFaturaImpressaoPorFatura(@Param("faturaId") Long faturaId);

   @Query("""
        SELECT  
            fatura
        FROM 
           Fatura fatura
           left join fetch fatura.pontoConsumo pontoConsumo 
           left join fetch fatura.contratoAtual contrato
           inner join fetch fatura.cliente cliente
        WHERE 1=1
           AND (cliente.id = :clienteId)
           AND (contrato.id = :contratoId)
           AND (pontoConsumo.id = :pontoConsumoId)
           AND (fatura.dataVencimento < :pDataAtual)
           AND (fatura.creditoDebitoSituacao.valido is true)
           AND (fatura.situacaoPagamento = :pSituacaoPagamento)
        ORDER BY fatura.dataVencimento desc   
        FETCH FIRST 3 ROWS ONLY
        """)
   List<Fatura> consultarFaturasVencidas(
           @Param("clienteId") Long clienteId,
           @Param("contratoId") Long contratoId,
           @Param("pontoConsumoId") Long pontoConsumoId,
           @Param("pDataAtual") LocalDate pDataAtual,
           @Param("pSituacaoPagamento") SituacaoPagamentoEnum pSituacaoPagamento
   );

   @Query("""
        SELECT  
            faturaItem.fatura
        FROM 
           FaturaItem faturaItem
        WHERE 1=1
           AND (faturaItem.fatura.anoMes = :anoMesReferencia)
           AND (faturaItem.fatura.ciclo = :ciclo)
        ORDER BY faturaItem.fatura.rota.faturamentoGrupo, faturaItem.rubrica.id asc   
   """)
   List<Fatura> consultarFaturasPorAnoMesCiclo(@Param("anoMesReferencia") Integer anoMesReferencia,
                                               @Param("ciclo") Integer ciclo);

   @Query("""
        SELECT  
            faturaItem.fatura
        FROM 
           FaturaItem faturaItem
        WHERE 1=1
           AND (faturaItem.fatura.anoMes = :anoMesReferencia)
        ORDER BY faturaItem.fatura.rota.faturamentoGrupo, faturaItem.rubrica.id asc   
   """)
   List<Fatura> consultarFaturasPorAnoMes(@Param("anoMesReferencia") Integer anoMesReferencia);

   @Query("""
        SELECT  
            fatura
        FROM 
           Fatura fatura
        WHERE 1=1
           AND (fatura.anoMes = :anoMesReferencia)
           AND (fatura.ciclo = :ciclo)
           AND (fatura.rota.id = :rotaId)
   """)
   List<Fatura> obterFaturasPorAnoMesCicloRota(
           @Param("anoMesReferencia") Integer anoMesReferencia,
           @Param("ciclo") Integer ciclo,
           @Param("rotaId") Long rotaId
   );

   @Query("""
            SELECT DISTINCT
                  fatu.id as id,
                  fatu.anoMes as anoMes,
                  fatu.ciclo as ciclo,
                  fatu.numeroFatura as numeroFatura,
                  fatu.valorTotal as valorTotal,
                  fatu.periodoCiclo as periodoCiclo,
                  pc.descricao as pontoConsumoDescricao,
                  segm.descricao as segmentoDescricao,
                  fatu.dataEmissao as dataEmissao,
                  fatu.dataVencimento as dataVencimento,
                  fatu.statusFatura as statusFatura,
                  fatu.situacaoPagamento as situacaoPagamento
            
            FROM
                Fatura fatu
            JOIN
                fatu.pontoConsumo pc
            JOIN
                fatu.segmento segm
            
            WHERE 1=1
                AND (:anoMes IS NULL OR (fatu.anoMes = :anoMes))
                AND (:ciclo IS NULL OR (fatu.ciclo = :ciclo))
                AND (:numeroFatura IS NULL OR (fatu.numeroFatura = :numeroFatura))
                AND (:valorTotal IS NULL OR (fatu.valorTotal = :valorTotal))
                AND (:periodoCiclo IS NULL OR UPPER(fatu.periodoCiclo) LIKE CONCAT('%',UPPER(:periodoCiclo),'%'))
                AND (:pontoConsumoDescricao IS NULL OR UPPER(pc.descricao) LIKE CONCAT('%',UPPER(:pontoConsumoDescricao),'%'))
                AND (:segmentoDescricao IS NULL OR UPPER(segm.descricao) LIKE CONCAT('%',UPPER(:segmentoDescricao),'%'))
                AND (:dataEmissao IS NULL OR (fatu.dataEmissao = :dataEmissao))
                AND (:dataVencimento IS NULL OR (fatu.dataVencimento = :dataVencimento))
                AND (:statusFatura IS NULL OR (fatu.statusFatura = :statusFatura))
                AND (:situacaoPagamento IS NULL OR (fatu.situacaoPagamento = :situacaoPagamento))
                AND (:habilitado IS NULL or fatu.habilitado = :habilitado)
            ORDER BY
                fatu.id ASC
            """)
   Page<FaturaBasicProjection> findAll(
           @Param("anoMes") Integer anoMes,
           @Param("ciclo") Integer ciclo,
           @Param("numeroFatura") Integer numeroFatura,
           @Param("valorTotal") BigDecimal valorTotal,
           @Param("periodoCiclo") String periodoCiclo,
           @Param("pontoConsumoDescricao") String pontoConsumoDescricao,
           @Param("segmentoDescricao") String segmentoDescricao,
           @Param("dataEmissao") LocalDate dataEmissao,
           @Param("dataVencimento") LocalDate dataVencimento,
           @Param("statusFatura") StatusFaturaEnum statusFaturaEnum,
           @Param("situacaoPagamento") SituacaoPagamentoEnum situacaoPagamentoEnum,
           @Param("habilitado") Boolean habilitado,
           Pageable pageable);

   @Query("""
   SELECT  
            fatura
        FROM 
           Fatura fatura
        WHERE 1=1
           AND (fatura.anoMes = :anoMesReferencia)
           AND (fatura.ciclo = :ciclo)
           AND (fatura.rota.id = :rotaId)
           AND (fatura.statusFatura = :status)
   """)
   List<Fatura> obterFaturasPorAnoMesCicloStatusRota(
           @Param("rotaId") Long rotaId,
           @Param("anoMesReferencia") Integer anoMesReferencia,
           @Param("ciclo") Integer ciclo,
           @Param("status") StatusFaturaEnum status
   );
}

