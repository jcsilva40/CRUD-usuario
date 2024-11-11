package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.AtividadeSistema;
import com.stfn2.ggas.domain.FaturamentoAtividadeCronograma;
import com.stfn2.ggas.domain.FaturamentoGrupo;
import com.stfn2.ggas.domain.dtos.FaturamentoAtividadeCronogrDTO;
import com.stfn2.ggas.domain.dtos.basic.FaturamentoAtividadeCronogrBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.FaturamentoAtividadeCronogrFilterDTO;
import com.stfn2.ggas.repositories.FaturamentoAtividadeCronogrRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaturamentoAtividadeCronogrService extends BaseService<FaturamentoAtividadeCronograma, FaturamentoAtividadeCronogrDTO, FaturamentoAtividadeCronogrBasicDTO, FaturamentoAtividadeCronogrFilterDTO, FaturamentoAtividadeCronogrRepository> {

   public FaturamentoAtividadeCronograma consultarCronogramaAtividadeFaturamento(AtividadeSistema atividadeSistema,
                                                                                 FaturamentoGrupo faturamentoGrupo) throws NegocioException {
      List<FaturamentoAtividadeCronograma> retorno = new ArrayList<>();

      retorno = repository.consultarCronogramaAtividadeFaturamento(faturamentoGrupo.getId(),
              faturamentoGrupo.getAnoMesFaturamento(), faturamentoGrupo.getNumeroCiclo(),atividadeSistema.getId());

      if(retorno.isEmpty()){
         retorno = new ArrayList<>();
      }

      return retorno.get(0);
   }
}