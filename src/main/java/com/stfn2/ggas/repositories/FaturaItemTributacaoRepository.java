package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturaItemTributacao;
import com.stfn2.ggas.domain.dtos.filter.FaturaItemTributacaoFilterDTO;
import com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.RelatorioAnaliseFaturamentoDTO;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaturaItemTributacaoRepository extends BaseRepository<FaturaItemTributacao, FaturaItemTributacaoFilterDTO> {


   @Query("""
        SELECT 
            new com.stfn2.ggas.services.componentes.relatorio.relatorioFaturamento.dto.RelatorioAnaliseFaturamentoDTO(
                fatu.id,
                fait.id,
                clie.nome,
                pocn.cil,
                pocn.id,
                pocn.descricao,
                rota.descricao,
                fagr.descricao,
                segm.descricao,
                mehi.dataLeituraFaturada,
                fatu.dataVencimento,
                cohi.diasConsumo,
                SUM(CASE WHEN tributo.descricao = 'ICMS' THEN fait.consumo ELSE java.math.BigDecimal.ZERO END),
                SUM(CASE WHEN tributo.descricao = 'ICMS' THEN fait.valorTotal ELSE java.math.BigDecimal.ZERO END),
                SUM(CASE WHEN tributo.descricao = 'ICMS' THEN fatb.baseCalculoTributo ELSE java.math.BigDecimal.ZERO END),
                SUM(CASE WHEN tributo.descricao = 'ICMS' THEN fatb.valorTributo ELSE java.math.BigDecimal.ZERO END),
                SUM(CASE WHEN tributo.descricao = 'ICMS' THEN fatb.valorTributoSubstitutivo ELSE java.math.BigDecimal.ZERO END),
                SUM(CASE WHEN tributo.descricao = 'ICMS' THEN fatb.valorTributoSubstitutivo ELSE java.math.BigDecimal.ZERO END),
                fatu.valorDiferimento,
                fatu.valorTotal
                )
       
        FROM 
            FaturaItemTributacao fatb
        INNER JOIN 
            fatb.faturaItem fait
        INNER JOIN 
            fait.fatura fatu
        INNER JOIN 
            fatu.cliente clie
        INNER JOIN 
            fatu.pontoConsumo pocn
        INNER JOIN 
            pocn.rota rota
        INNER JOIN 
            fatu.segmento segm
        INNER JOIN 
            fatu.consumoHistorico cohi
        INNER JOIN 
            fatu.medicaoHistorico mehi
        INNER JOIN 
            rota.faturamentoGrupo fagr
        INNER JOIN 
            fatb.tributo tributo
        WHERE 1=1 
            and fatu.anoMes = :anoMes
            and fatu.ciclo = :ciclo
            and rota.faturamentoGrupo.id = :idGrupoFaturamento
            and fatu.habilitado = true
            and fait.rubrica.id = :rubricaGas
         GROUP BY
            fatu.id,
            fait.id,
            clie.nome,
            pocn.cil,
            pocn.id,
            pocn.descricao,
            rota.descricao,
            fagr.descricao,
            segm.descricao,
            mehi.dataLeituraFaturada,
            fatu.dataVencimento,
            cohi.diasConsumo,
            fatu.valorDiferimento,
            fatu.valorTotal  
        """)
   List<RelatorioAnaliseFaturamentoDTO> obterRelatorioAnaliseFaturamento(@Param("idGrupoFaturamento") Long idGrupoFaturamento,
                                                                         @Param("rubricaGas") Long rubricaGas,
                                                                         @Param("anoMes") Integer anoMes,
                                                                         @Param("ciclo") Integer ciclo);

}