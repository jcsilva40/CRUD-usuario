package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturaImpressao;
import com.stfn2.ggas.domain.dtos.filter.FaturaImpressaoFilterDTO;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FaturaImpressaoRepository extends BaseRepository<FaturaImpressao, FaturaImpressaoFilterDTO> {

   @Query("""
        SELECT  faturaImpressao
             
        FROM 
           FaturaImpressao faturaImpressao
        WHERE 1=1
           AND (faturaImpressao.fatura.id = :faturaId)
        """)
   FaturaImpressao obterFaturaImpressaoPorFatura(@Param("faturaId") Long faturaId);

   @Query("""
        SELECT  faturaImpressao
             
        FROM 
           FaturaImpressao faturaImpressao
        WHERE 1=1
           AND (faturaImpressao.fatura.id = :faturaId)
        """)
   FaturaImpressao obterDataGeracaoFaturaImpressaoPorFatura(@Param("faturaId") Long faturaId);
}