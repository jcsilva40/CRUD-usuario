package com.stfn2.ggas.repositories;

import com.stfn2.ggas.domain.dtos.filter.IndiceFinanceiroValorNominalFilterDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.IndiceFinanceiroValorNominal;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IndiceFinanceiroValorNominalRepository extends BaseRepository<IndiceFinanceiroValorNominal, IndiceFinanceiroValorNominalFilterDTO> {
    @Query(""" 
            SELECT vlrNominal
            FROM IndiceFinanceiroValorNominal vlrNominal 
            INNER JOIN vlrNominal.indiceFinanceiro indiceFinanceiro 
            WHERE(indiceFinanceiro.id =:pIdIndiceFinanceiro)
            AND (vlrNominal.dataReferencia BETWEEN :pDataInicial AND :pDataFinal) 
            ORDER BY (vlrNominal.dataReferencia)
            """)
    List<IndiceFinanceiroValorNominal> findByIndiceFinanceiroIdAndDataReferenciaBetween(@Param("pIdIndiceFinanceiro") Long pIdIndiceFinanceiro, @Param("pDataInicial") LocalDate pDataInicial, @Param("pDataFinal") LocalDate pDataFinal);

    @Query(""" 
            SELECT DISTINCT indiceFinanceiro.id
            FROM IndiceFinanceiroValorNominal vlrNominal 
            INNER JOIN vlrNominal.indiceFinanceiro indiceFinanceiro 
            WHERE (vlrNominal.dataReferencia BETWEEN :pDataInicial AND :pDataFinal)
            """)
    List<Long> findIdIndicesFinanceirosBetweenData(@Param("pDataInicial") LocalDate pDataInicial, @Param("pDataFinal") LocalDate pDataFinal);

    @Query("""
    SELECT vlrNominal
    FROM IndiceFinanceiroValorNominal vlrNominal
    INNER JOIN vlrNominal.indiceFinanceiro indiceFinanceiro
    WHERE indiceFinanceiro.id = :pIdIndiceFinanceiro
    AND vlrNominal.dataReferencia <= :pDataFinal
    ORDER BY vlrNominal.dataReferencia DESC
    """)
    List<IndiceFinanceiroValorNominal> findMaisRecenteValorNominalByIndiceFinanceiroId(
            @Param("pIdIndiceFinanceiro") Long pIdIndiceFinanceiro,
            @Param("pDataFinal") LocalDate pDataFinal);


}
