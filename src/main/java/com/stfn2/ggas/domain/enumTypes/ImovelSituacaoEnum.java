package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NoArgsConstructor;

//@NoArgsConstructor
public enum ImovelSituacaoEnum {

   POTENCIAL(1l, "Potencial"),
   FACTIVEL(2l, "Factível"),
   INTERLIGADO(3l, "Interligado"),
   NAO_LIBERADO(4l, "Não Liberado"),
   EM_PROSPECCAO(5l, "Em Prospecção"),
   PROSPECTADO(6l, "Prospectado");


   private Long id;
   private String descricao;

   private ImovelSituacaoEnum(Long id, String descricao) {
      this.id = id;
      this.descricao=descricao;
   }

   private ImovelSituacaoEnum() {

   }
   @JsonValue
   public Long getId() {
      return id;
   }

   public String getDescricao() {
      return descricao;
   }

   public static ImovelSituacaoEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (ImovelSituacaoEnum x : ImovelSituacaoEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }

}
