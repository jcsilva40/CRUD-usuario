package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturaDadosTecnicos;
import com.stfn2.ggas.domain.dtos.filter.FaturaDadosTecnicosFilterDTO;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaturaDadosTecnicosRepository extends BaseRepository<FaturaDadosTecnicos, FaturaDadosTecnicosFilterDTO> {

   @Query("""
        SELECT 
            dadosTecnicos
        FROM FaturaDadosTecnicos dadosTecnicos
        WHERE 1=1
           AND (dadosTecnicos.fatura.id =:faturaId)
        """)
   List<FaturaDadosTecnicos> obterPorChaveFatura(@Param("faturaId") Long faturaId);
}