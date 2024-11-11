package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Periodicidade;
import com.stfn2.ggas.domain.RamoAtividade;
import com.stfn2.ggas.domain.Segmento;
import com.stfn2.ggas.domain.dtos.RamoAtividadeSubstituicaoTributariaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RamoAtividadeBasicDTO extends BaseDTO {
   private Long id;
   private Segmento segmento;
   private Periodicidade periodicidade;
   private String descricao;
   private BigDecimal tipoConsumoFaturamento;
   private Boolean habilitado;

   private List<RamoAtividadeSubstituicaoTributariaDTO> ramoAtividadeSubsTrib = new ArrayList<>();

   public RamoAtividadeBasicDTO(RamoAtividade entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}