package com.stfn2.ggas.domain.dtos.basic;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.*;
import com.stfn2.ggas.domain.enumTypes.PontoConsumoSituacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PontoConsumoBasicDTO extends BaseDTO {
   private Long id;
   private String descricao;
   private String cil;
   private Long ramoAtividadeId;
   private String ramoAtividadeDescricao;
   private Long segmentoId;
   private String segmentoDescricao;
   private PontoConsumoSituacaoEnum pontoConsumoSituacao;
   private Long rotaId;
   private String rotaDescricao;
   private Long imovelId;
   private String imovelDescricao;
   private Long medidorInstalacaoId;
   private Boolean habilitado = true;

   public PontoConsumoBasicDTO(PontoConsumo entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}