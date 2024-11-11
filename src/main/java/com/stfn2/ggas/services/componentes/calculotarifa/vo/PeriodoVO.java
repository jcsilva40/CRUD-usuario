package com.stfn2.ggas.services.componentes.calculotarifa.vo;

import java.io.Serializable;
import java.time.LocalDate;

public class PeriodoVO implements Serializable {

   private static final long serialVersionUID = 1L;

   private LocalDate dataInicio;
   private LocalDate dataFim;

   public Integer getQuantidadeDiasIntervalo(){
      Integer quantidadeDiasIntervalo = 0;
      LocalDate dataInicio = this.dataInicio;
      while(dataInicio.isBefore(dataFim)){
         quantidadeDiasIntervalo++;
         dataInicio = dataInicio.plusDays(1);
      }

      return quantidadeDiasIntervalo;
   }

}
