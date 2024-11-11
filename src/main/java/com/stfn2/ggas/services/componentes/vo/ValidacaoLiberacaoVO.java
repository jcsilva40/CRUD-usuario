package com.stfn2.ggas.services.componentes.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidacaoLiberacaoVO {
   private Long id;
   private Integer anoMes;
   private Integer ciclo;
   private Long idSegmento;
   private Long idFatura;
   private Long idStatus;

   public ValidacaoLiberacaoVO(){
      this.anoMes = 0;
      this.ciclo = 0;
      this.idSegmento = 0L;
      this.idFatura = 0L;
      this.idStatus = 0L;
   }
}
