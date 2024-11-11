package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Menu;
import com.stfn2.ggas.domain.RecursoSistema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO extends BaseDTO {
   private Long id;
   private Menu menuPai;
   private RecursoSistema recursoSistema;
   private String descricao;
   private String descricaoUrl;
   private Integer ordem;
   private Boolean alcada;
   private Boolean exibirFilhos;
   private Boolean habilitado;

   public MenuDTO(Menu entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}