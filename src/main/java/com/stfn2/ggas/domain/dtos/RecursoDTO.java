package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Recurso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecursoDTO extends BaseDTO {
   private Long id;
   private String url;
   private String verboHttp;
   private Boolean considerarVerbo;

   public RecursoDTO(Recurso entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}