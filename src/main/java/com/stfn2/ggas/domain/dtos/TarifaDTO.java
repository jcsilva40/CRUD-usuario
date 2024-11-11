package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.RamoAtividade;
import com.stfn2.ggas.domain.Segmento;
import com.stfn2.ggas.domain.Tarifa;
import com.stfn2.ggas.domain.TarifaVigencia;
import com.stfn2.ggas.domain.enumTypes.ItemFaturaEnum;
import com.stfn2.ggas.domain.enumTypes.TipoModeloContratoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarifaDTO extends BaseDTO {

   private Long id;
   private Segmento segmento;
   private RamoAtividade ramoAtividade;
   private ItemFaturaEnum itemFatura;
   private TipoModeloContratoEnum tipoContrato;
   private String descricao;
   private String abreviado;
   private Integer casaDecimal;
   private String nomeSefaz;
   private List<TarifaVigencia> vigencias;
   private Boolean habilitado = true;

   public TarifaDTO(Tarifa entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}
