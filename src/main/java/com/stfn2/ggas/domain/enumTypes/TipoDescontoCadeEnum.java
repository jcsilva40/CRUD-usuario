package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoDescontoCadeEnum {

   MARGEM(0L, "Margem", ""),
   PRECO_GAS(1L, "Preço do Gás", ""),   
   VOLUME(2L, "Por Volume", "");
   
   private final Long id;
   private final String descricao; 
   private final String abreviado;

   TipoDescontoCadeEnum(Long id, String descricao, String abreviado) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;      
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public static TipoDescontoCadeEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (TipoDescontoCadeEnum x : TipoDescontoCadeEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}