package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.PrecoMedioPonderado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrecoMedioPonderadoDTO extends BaseDTO {
   private Long id;
   private Integer referencia;
   private LocalDate dataFim;
   private LocalDate dataVigencia;
   private BigDecimal valor;
   private Boolean habilitado = true;

   public PrecoMedioPonderadoDTO(PrecoMedioPonderado entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}