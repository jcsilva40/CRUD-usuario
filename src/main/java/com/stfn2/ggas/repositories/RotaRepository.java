package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.Rota;
import com.stfn2.ggas.domain.dtos.filter.RotaFilterDTO;
import com.stfn2.ggas.domain.projection.RotaBasicProjection;
import com.stfn2.ggas.services.componentes.faturamentogruporota.vo.RotaFaturamentoGrupoVO;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RotaRepository extends BaseRepository<Rota, RotaFilterDTO> {

   @Query("""
        
        SELECT rota
          FROM Rota rota 
          WHERE (rota.faturamentoGrupo.id =:idFaturamentoGrupo)
          
       
        """)
   List<Rota> obterRotasPeloFaturamentoGrupo(@Param("idFaturamentoGrupo") Long idFaturamentoGrupo);

   @Query("""
            SELECT new com.stfn2.ggas.services.componentes.faturamentogruporota.vo.RotaFaturamentoGrupoVO
                (
                rota.id,
                rota.descricao
                )
            FROM
                Rota rota
            WHERE
                rota.faturamentoGrupo.id = :faturamentoGrupoId
            """)
   List<RotaFaturamentoGrupoVO> listaRotaFaturamentoGrupo(@Param Long faturamentoGrupoId);

   @Query("""
            SELECT DISTINCT
                  rota.id as id,
                  rota.descricao as descricao,
                  fatgr.id as idFaturamentoGrupo,
                  fatgr.descricao as faturamentoGrupoDescricao,
                  peri.id as idPeriodicidade,
                  peri.descricao as periodicidadeDescricao,
                  SIZE(rota.listaImoveis) as quantidadeImoveis
            
            FROM
                Rota rota
            JOIN
                rota.faturamentoGrupo fatgr
            JOIN
                rota.periodicidade peri
            
            WHERE 1=1
                AND (:descricao IS NULL OR UPPER(rota.descricao) LIKE CONCAT('%',UPPER(:descricao),'%'))
                AND (:faturamentoGrupo IS NULL OR UPPER(fatgr.descricao) LIKE CONCAT('%',UPPER(:faturamentoGrupo),'%'))
                AND (:periodicidade IS NULL OR UPPER(peri.descricao) LIKE CONCAT('%',UPPER(:periodicidade),'%'))
                AND (:quantidadeImoveis IS NULL OR SIZE(rota.listaImoveis) = :quantidadeImoveis)
                AND (:habilitado IS NULL or rota.habilitado = :habilitado)
            ORDER BY
                rota.id DESC
            """)
   Page<RotaBasicProjection> findAll(
           @Param("descricao") String descricao,
           @Param("faturamentoGrupo") String faturamentoGrupo,
           @Param("periodicidade") String periodicidade,
           @Param("quantidadeImoveis") Integer quantidadeImoveis,
           @Param("habilitado") Boolean habilitado,
           Pageable pageable);
}
