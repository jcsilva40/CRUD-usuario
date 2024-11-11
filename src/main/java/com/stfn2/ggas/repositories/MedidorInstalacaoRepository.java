package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.MedidorInstalacao;
import com.stfn2.ggas.domain.dtos.filter.MedidorInstalacaoFilterDTO;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedidorInstalacaoRepository extends BaseRepository<MedidorInstalacao, MedidorInstalacaoFilterDTO> {

    @Query("""
        
        SELECT instalacao
          FROM MedidorInstalacao instalacao 
          WHERE (instalacao.pontoConsumo.id =:idPontoConsumo)
          
       
        """)
    List<MedidorInstalacao> obterInstalacaoPorPontoConsumo(@Param("idPontoConsumo") Long idPontoConsumo);

}