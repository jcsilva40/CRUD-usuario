package com.stfn2.ggas.repositories;

import com.stfn2.ggas.domain.ContratoPontoConsumo;
import com.stfn2.ggas.domain.dtos.ImovelDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Imovel;
import com.stfn2.ggas.domain.dtos.filter.ImovelFilterDTO;

import java.util.List;

@Repository
public interface ImovelRepository extends BaseRepository<Imovel, ImovelFilterDTO> {

    @Query("""
           SELECT cpc 
           FROM ContratoPontoConsumo cpc 
           INNER JOIN cpc.pontoConsumo pc 
           INNER JOIN cpc.contrato cont
           WHERE pc.id = :id
           AND cont.contratoSituacao IN (ContratoSituacaoEnum.ATIVO, ContratoSituacaoEnum.ALTERADO)
           """)
    List<ContratoPontoConsumo> findByPontoConsumoId(@Param("id") Long id);
    
    @Query("""
           SELECT pocn.imovel 
           FROM PontoConsumo pocn 
           WHERE pocn.id = :pontoConsumoId
           """)
    Imovel findImovelByPontoConsumoId(@Param("pontoConsumoId") Long pontoConsumoId);

    @Query("""
            SELECT
                i
            FROM
                Imovel i
            WHERE
                i.rota.id IS NULL
            """)

    List<ImovelDTO> obterListaImoveisDisponiveis();

}