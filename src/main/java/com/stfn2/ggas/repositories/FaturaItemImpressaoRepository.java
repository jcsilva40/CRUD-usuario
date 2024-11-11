package com.stfn2.ggas.repositories;

import com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.SubGasNaturalDTO;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturaItemImpressao;
import com.stfn2.ggas.domain.dtos.filter.FaturaItemImpressaoFilterDTO;

import java.util.List;

@Repository
public interface FaturaItemImpressaoRepository extends BaseRepository<FaturaItemImpressao, FaturaItemImpressaoFilterDTO> {

   @Query("""
        SELECT 
            new com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.SubGasNaturalDTO(
              COALESCE(faii.descricaoItem, "Consumo de Gás" ),
              faii.consumo,
              faii.valorUnitario,
              faii.valorTotal,
              faii.valorDesconto
            )           
        FROM 
            FaturaItemImpressao faii 
        WHERE 1=1
            AND (faii.faturaItem.id = :faturaItemId)
        GROUP BY 
            faii.descricaoItem,
            faii.consumo,
            faii.valorUnitario,
            faii.valorTotal,
            faii.valorDesconto
        """)
   List<SubGasNaturalDTO> obterSubGasNatural(@Param("faturaItemId") Long faturaItemId);

   @Query("""
        SELECT 
            faii        
        FROM 
            FaturaItemImpressao faii 
        WHERE 1=1
            AND (faii.faturaItem.id in (:itensFaturaId))
        ORDER BY faii.desconto    
        """)
   List<FaturaItemImpressao> obterListaFaturaItemImpressaoPorFaturaItem(@Param("itensFaturaId") Long[] itensFaturaId);

   @Query("""
        SELECT 
            faii        
        FROM 
            FaturaItemImpressao faii 
        WHERE 1=1
            AND (faii.faturaItem.id  = :faturaItemId)
        ORDER BY faii.desconto    
        """)
   List<FaturaItemImpressao> obterListaFaturaItemImpressaoPorIdFaturaItem(@Param("faturaItemId") Long faturaItemId);
}