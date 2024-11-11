package com.stfn2.ggas.repositories;

import com.stfn2.ggas.domain.AtividadeSistema;
import com.stfn2.ggas.domain.FaturamentoAtividadeCronograma;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturamentoCronograma;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoCronogramaFilterDTO;

import java.util.List;

@Repository
public interface FaturamentoCronogramaRepository extends BaseRepository<FaturamentoCronograma, FaturamentoCronogramaFilterDTO> {

   @Query("""
          SELECT cronograma
          FROM
           FaturamentoCronograma cronograma 
          WHERE cronograma.habilitado = true 
          and (cronograma.faturamentoGrupo.id =:idFaturamentoGrupo)
          and (cronograma.anoMesFaturamento =:anoMes)
          and (cronograma.ciclo =:ciclo)
          ORDER BY cronograma.id
          LIMIT 1
        """)
   FaturamentoCronograma obterCronograma(@Param("idFaturamentoGrupo") Long idFaturamentoGrupo,
                                         @Param("anoMes") Integer anoMes,
                                         @Param("ciclo") Integer ciclo);

   @Query("""
          SELECT cronograma
          FROM
           FaturamentoCronograma cronograma 
           inner join fetch cronograma.faturamentoGrupo grupo
           inner join fetch grupo.periodicidade periodicidade
          WHERE cronograma.habilitado = true 
          and (grupo.id = :FaturamentoGrupoId)
          order by cronograma.anoMesFaturamento desc, cronograma.ciclo desc
          LIMIT 1
          """)
   FaturamentoCronograma obterUltimoCronogramaFaturamentoPorGrupoFaturamento(@Param("FaturamentoGrupoId") Long FaturamentoGrupoId);

   @Query("""
          SELECT distinct cronogramaAtividadeFaturamento
          FROM FaturamentoAtividadeCronograma cronogramaAtividadeFaturamento 
               inner join fetch cronogramaAtividadeFaturamento.atividadeSistema atividadeSistema
               inner join fetch cronogramaAtividadeFaturamento.faturamentoCronograma cronogramaFaturamento
               left join fetch atividadeSistema.atividadeSistemaPrecedente atividadePrecedente
          WHERE cronogramaAtividadeFaturamento.habilitado = true 
            and (cronogramaAtividadeFaturamento.faturamentoCronograma.id = :faturamentoCronogramaId)
          order by atividadeSistema.sequencia
        """)
   List<FaturamentoAtividadeCronograma> consultarCronogramaAtivFaturamentoPorCronogramaFaturamento(@Param("faturamentoCronogramaId") Long faturamentoCronogramaId);

   @Query("""
          SELECT atividadeSistema
          FROM AtividadeSistema atividadeSistema 
               left join fetch atividadeSistema.moduloSistema modulo
               left join fetch atividadeSistema.operacaoSistema operacao
          WHERE atividadeSistema.habilitado = true 
            and (atividadeSistema.cronograma = true)
          order by atividadeSistema.sequencia asc
        """)
   List<AtividadeSistema> listarAtividadeSistemaPorCronograma();

   @Query("""
          SELECT cronograma
          FROM
           FaturamentoCronograma cronograma 
           inner join fetch cronograma.faturamentoGrupo grupo
           inner join fetch grupo.periodicidade periodicidade
          WHERE cronograma.habilitado = true 
          and (grupo.id = :FaturamentoGrupoId)
          """)
   List<FaturamentoCronograma> listarCronogramaFaturamentosPorGrupo(@Param("FaturamentoGrupoId") Long FaturamentoGrupoId);

   @Query("""
          SELECT MIN(faturamentoGrupo.anoMesFaturamento)
          FROM
           FaturamentoGrupo faturamentoGrupo
          WHERE faturamentoGrupo.habilitado = true 
          and (select count(*) from FaturamentoCronograma faturamentoCronograma 
                WHERE faturamentoCronograma.habilitado = true   
                AND faturamentoCronograma.faturamentoGrupo.id = faturamentoGrupo.id) > 0
          """)
   Integer consultarMenorReferenciaCicloGrupoFaturamentoComCronograma();

}