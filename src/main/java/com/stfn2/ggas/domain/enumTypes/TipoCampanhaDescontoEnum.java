package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoCampanhaDescontoEnum {

   CAPTACAO(0L, "Captação", ""),
   RETENCAO(1L, "Retenção", "");
   
   private final Long id;
   private final String descricao; 
   private final String abreviado;

   TipoCampanhaDescontoEnum(Long id, String descricao, String abreviado) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;      
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public static TipoCampanhaDescontoEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (TipoCampanhaDescontoEnum x : TipoCampanhaDescontoEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}