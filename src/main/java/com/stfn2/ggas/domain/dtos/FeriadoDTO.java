package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Feriado;
import com.stfn2.ggas.domain.Municipio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeriadoDTO extends BaseDTO {
   private Long id;
   private Long municipioId;
   private LocalDate dataFeriado;
   private String descricao;
   private Boolean feriadoMunicipal = true;
   private Boolean sempreMesmoDia = true;
   private Integer anosFeriado;

   public FeriadoDTO(Feriado entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}