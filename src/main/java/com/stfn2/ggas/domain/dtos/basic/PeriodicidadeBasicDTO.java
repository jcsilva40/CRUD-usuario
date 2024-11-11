package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Periodicidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeriodicidadeBasicDTO extends BaseDTO {
   private Long id;
   private String descricao;
   private String abreviado;
   private Boolean mesCivil;
   private Integer maximoDiasCiclo;
   private Integer minimoDiasCiclo;
   private Integer quantidadeDias;
   private Integer quantidadeCiclos;

   public PeriodicidadeBasicDTO(Periodicidade entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}