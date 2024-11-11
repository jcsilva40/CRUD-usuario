package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturaHistorico;
import com.stfn2.ggas.domain.dtos.filter.FaturaHistoricoFilterDTO;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaturaHistoricoRepository extends BaseRepository<FaturaHistorico, FaturaHistoricoFilterDTO> {

   @Query("""
        SELECT 
            historico
        FROM FaturaHistorico historico
        WHERE 1=1
           AND (historico.fatura.id =:faturaId)
        """)
   List<FaturaHistorico> obterPorChaveFatura(@Param("faturaId") Long faturaId);
}