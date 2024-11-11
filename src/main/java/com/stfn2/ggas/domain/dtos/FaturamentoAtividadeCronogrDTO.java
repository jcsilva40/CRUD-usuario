package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.AtividadeSistema;
import com.stfn2.ggas.domain.FaturamentoAtividadeCronograma;
import com.stfn2.ggas.domain.FaturamentoCronograma;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturamentoAtividadeCronogrDTO extends BaseDTO {
   private Long id;
   private AtividadeSistema atividadeSistema;
   private FaturamentoCronograma faturamentoCronograma;
   private LocalDateTime dataInicio;
   private LocalDateTime dataFim;
   private LocalDateTime dataRealizacao;

   public FaturamentoAtividadeCronogrDTO(FaturamentoAtividadeCronograma entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}