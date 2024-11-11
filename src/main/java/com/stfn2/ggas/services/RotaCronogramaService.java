package com.stfn2.ggas.services;

import com.stfn2.ggas.config.exceptions.types.NegocioException;
import com.stfn2.ggas.core.abstractClasses.BaseService;
import com.stfn2.ggas.domain.FaturamentoAtividadeCronograma;
import com.stfn2.ggas.domain.FaturamentoCronograma;
import com.stfn2.ggas.domain.RotaCronograma;
import com.stfn2.ggas.domain.dtos.RotaCronogramaDTO;
import com.stfn2.ggas.domain.dtos.basic.RotaCronogramaBasicDTO;
import com.stfn2.ggas.domain.dtos.filter.RotaCronogramaFilterDTO;
import com.stfn2.ggas.repositories.RotaCronogramaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RotaCronogramaService extends BaseService<RotaCronograma, RotaCronogramaDTO, RotaCronogramaBasicDTO, RotaCronogramaFilterDTO, RotaCronogramaRepository> {

   public List<RotaCronograma> consultarRotaCronogramaPorFaturamentoCronograma(FaturamentoCronograma cronogramaFaturamento) {

      List<RotaCronograma> rotasCronograma = new ArrayList<>();

      rotasCronograma = repository.consultarRotaCronogramaPorFaturamentoCronograma(cronogramaFaturamento.getId());

      return rotasCronograma;
   }

   public List<RotaCronograma> consultarCronogramaRota(FaturamentoAtividadeCronograma faturamentoAtividadeCronograma) {

      List<RotaCronograma> rotasCronograma = new ArrayList<>();

      rotasCronograma = repository.consultarCronogramaRota(faturamentoAtividadeCronograma.getId());

      return rotasCronograma;
   }
}