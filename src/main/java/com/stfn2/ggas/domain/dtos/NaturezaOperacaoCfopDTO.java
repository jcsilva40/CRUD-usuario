package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.EntidadeConteudo;
import com.stfn2.ggas.domain.NaturezaOperacao;
import com.stfn2.ggas.domain.NaturezaOperacaoCfop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NaturezaOperacaoCfopDTO extends BaseDTO {
   private Long id;
   private NaturezaOperacao naturezaOperacao;
   private EntidadeConteudo localizacaoDestinatario;
   private Integer codigoFiscal = 0;

   public NaturezaOperacaoCfopDTO(NaturezaOperacaoCfop entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}