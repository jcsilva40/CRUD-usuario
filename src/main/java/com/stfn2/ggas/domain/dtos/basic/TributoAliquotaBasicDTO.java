package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Municipio;
import com.stfn2.ggas.domain.Tributo;
import com.stfn2.ggas.domain.TributoAliquota;
import com.stfn2.ggas.domain.enumTypes.TipoAplicacaoEnum;
import com.stfn2.ggas.domain.enumTypes.TipoAplicacaoReducaoEnum;
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
public class TributoAliquotaBasicDTO extends BaseDTO {
   private Long id;
   private Tributo tributo;
   private TipoAplicacaoEnum tipoAplicacao;
   private TipoAplicacaoReducaoEnum tipoAplicacaoReducaoBaseCalculo;
   private Municipio municipio;
   private LocalDate dataVigencia;
   private BigDecimal valorAliquota;
   private BigDecimal valorReducaoBaseCalculo;
   private Boolean reducaoBaseCalculo;
   private Boolean habilitado;

   public TributoAliquotaBasicDTO(TributoAliquota entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}