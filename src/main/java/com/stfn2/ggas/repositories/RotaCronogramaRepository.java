package com.stfn2.ggas.repositories;

import com.stfn2.ggas.core.interfaces.BaseRepository;
import com.stfn2.ggas.domain.RotaCronograma;
import com.stfn2.ggas.domain.dtos.filter.RotaCronogramaFilterDTO;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RotaCronogramaRepository extends BaseRepository<RotaCronograma, RotaCronogramaFilterDTO> {


   @Query("""
          SELECT rotaCronograma
          FROM
           RotaCronograma rotaCronograma 
           inner join rotaCronograma.faturamentoAtividadeCronograma atividadeCronograma
          WHERE rotaCronograma.habilitado = true 
          and (atividadeCronograma.faturamentoCronograma.id = :FaturamentoCronogramaId)
          """)
   List<RotaCronograma> consultarRotaCronogramaPorFaturamentoCronograma(@Param("FaturamentoCronogramaId") Long FaturamentoCronogramaId);

   @Query("""
          SELECT rotaCronograma
          FROM
           RotaCronograma rotaCronograma 
          WHERE rotaCronograma.habilitado = true 
          and (rotaCronograma.faturamentoAtividadeCronograma.id = :FaturamentoAtividadeCronogramaId)
          """)
   List<RotaCronograma> consultarCronogramaRota(@Param("FaturamentoAtividadeCronogramaId") Long FaturamentoAtividadeCronogramaId);
}