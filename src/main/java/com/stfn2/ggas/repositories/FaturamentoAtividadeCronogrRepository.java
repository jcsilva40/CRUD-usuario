package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.FaturamentoAtividadeCronograma;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoAtividadeCronogrFilterDTO;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaturamentoAtividadeCronogrRepository extends BaseRepository<FaturamentoAtividadeCronograma, FaturamentoAtividadeCronogrFilterDTO> {

   @Query("""
          SELECT faturamentoAtividadeCronograma
          FROM
           FaturamentoAtividadeCronograma faturamentoAtividadeCronograma
           inner join faturamentoAtividadeCronograma.faturamentoCronograma faturamentoCronograma
           inner join faturamentoAtividadeCronograma.atividadeSistema atividadeSistema
          WHERE 1=1 
          and (faturamentoCronograma.faturamentoGrupo.id = :faturamentoGrupoId)
          and (faturamentoCronograma.anoMesFaturamento = :anoMes)
          and (faturamentoCronograma.ciclo = :ciclo)
          and (atividadeSistema.id = :atividadeSistemaId)
          """)
   List<FaturamentoAtividadeCronograma> consultarCronogramaAtividadeFaturamento(@Param("faturamentoGrupoId") Long faturamentoGrupoId,
                                                                                @Param("anoMes") Integer anoMes,
                                                                                @Param("ciclo") Integer ciclo,
                                                                                @Param("atividadeSistemaId") Long atividadeSistemaId);
}