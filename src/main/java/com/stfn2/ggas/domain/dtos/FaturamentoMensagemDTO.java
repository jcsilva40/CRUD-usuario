package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.EntidadeConteudo;
import com.stfn2.ggas.domain.FaturamentoMensagem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturamentoMensagemDTO extends BaseDTO {
   private Long id;
   private EntidadeConteudo tipoMensagem;
   //	private Imovel imovel;
   private LocalDateTime dataInicioVigencia;
   private LocalDateTime dataFimVigencia;
   private String descricao;

   public FaturamentoMensagemDTO(FaturamentoMensagem entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}