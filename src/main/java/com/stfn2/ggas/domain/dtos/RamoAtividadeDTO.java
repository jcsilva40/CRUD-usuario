package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.RamoAtividade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RamoAtividadeDTO extends BaseDTO {
   
   private Long id;
   private Long idSegmento;
   private String descricao;
   private Long periodicidadeId;
   private Long tipoVisualizacaoFatura;

   private Long tipoSubstituicao;
   private Long valorSubstituto;
   private BigDecimal percentualSubstituto;
   private LocalDate dataInicioVigencia;
   private LocalDate dataFimVigencia;

   private List<RamoAtividadeSubstituicaoTributariaDTO> ramoAtividadeSubsTrib = new ArrayList<>();


   public RamoAtividadeDTO(RamoAtividade obj) {
      super();
   }
}
