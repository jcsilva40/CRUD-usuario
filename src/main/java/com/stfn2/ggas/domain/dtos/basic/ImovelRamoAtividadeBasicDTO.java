package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Imovel;
import com.stfn2.ggas.domain.ImovelRamoAtividade;
import com.stfn2.ggas.domain.RamoAtividade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImovelRamoAtividadeBasicDTO extends BaseDTO {
   private Long id;
   private Imovel imovel;
   private RamoAtividade ramoAtividade;
   private Integer quantidadeUnidadeConsumidoras = 0;
   private Boolean habilitado;

   public ImovelRamoAtividadeBasicDTO(ImovelRamoAtividade entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}