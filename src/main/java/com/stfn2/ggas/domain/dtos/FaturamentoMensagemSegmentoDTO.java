package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.FaturamentoMensagem;
import com.stfn2.ggas.domain.FaturamentoMensagemSegmento;
import com.stfn2.ggas.domain.RamoAtividade;
import com.stfn2.ggas.domain.Segmento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturamentoMensagemSegmentoDTO extends BaseDTO {
   private Long id;
   private FaturamentoMensagem faturamentoMensagem;
   private Segmento segmento;
   private RamoAtividade ramoAtividade;

   public FaturamentoMensagemSegmentoDTO(FaturamentoMensagemSegmento entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}