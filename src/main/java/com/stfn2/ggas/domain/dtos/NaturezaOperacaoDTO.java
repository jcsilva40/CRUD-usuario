package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.EntidadeConteudo;
import com.stfn2.ggas.domain.NaturezaOperacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NaturezaOperacaoDTO extends BaseDTO {
   private Long id;
   private EntidadeConteudo tipoOperacao;
   private String descricao;
   private String notaExplicativa;

   public NaturezaOperacaoDTO(NaturezaOperacao entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}