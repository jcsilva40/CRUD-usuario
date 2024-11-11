package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturaItem;
import com.stfn2.ggas.domain.dtos.filter.FaturaItemFilterDTO;
import com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.SubJurosDTO;
import com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.SubMultaDTO;
import com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.ValoresRubricasDTO;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaturaItemRepository extends BaseRepository<FaturaItem, FaturaItemFilterDTO> {

    List<FaturaItem> findByCreditoDebitoARealizarId(Long creditoDebitoARealizarId);

    @Query("""
        SELECT 
            new com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.ValoresRubricasDTO(
                  fait.quantidadeItem,
                  fait.valorTotal
            )
        FROM 
           FaturaItem fait 
        WHERE 1=1
           AND (fait.fatura.id = :faturaId)
           AND (fait.rubrica.id = :rubricaId)
        """)
    List<ValoresRubricasDTO> obterItensPorRubrica(@Param("faturaId") Long faturaId,
                                                  @Param("rubricaId") Long rubricaId);

    @Query("""
        SELECT 
           fait.rubrica.id
        FROM 
           FaturaItem fait 
        WHERE 1=1
           AND (fait.fatura.id = :faturaId)
           AND (fait.rubrica.id NOT IN :listaRubricasIgnorar)
        """)
    List<Long> obterRubricas(@Param("faturaId") Long faturaId,
                             @Param("listaRubricasIgnorar") List<Long> listaRubricasIgnorar);


    @Query("""
        SELECT 
            new com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.SubJurosDTO(
                fait.quantidadeItem,
                fait.valorUnitario,
                fait.valorTotal
            )
        FROM
           FaturaItem fait
        WHERE 1=1
           AND(fait.fatura.id = :faturaId)
           AND (fait.rubrica.id = :rubricaJurosId)
        """)
    List<SubJurosDTO> obterDadosJuros(@Param("faturaId") Long faturaId,
                                      @Param("rubricaJurosId")  Long rubricaJurosId);


    @Query("""
        SELECT 
            new com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.SubMultaDTO(
                fait.quantidadeItem,
                fait.valorUnitario,
                fait.valorTotal
            )
        FROM FaturaItem fait
        WHERE 1=1
           AND (fait.fatura.id = :faturaId)
           AND (fait.rubrica.id = :rubricaMultaId)
        """)
    List<SubMultaDTO> obterDadosMulta(@Param("faturaId") Long faturaId,
                                      @Param("rubricaMultaId")  Long rubricaMultaId);
}