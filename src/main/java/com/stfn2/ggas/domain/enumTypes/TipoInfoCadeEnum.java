package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoInfoCadeEnum {

   CONTRATO(0L, "CONTRATO", "CONT"),
   IMOVEL(1L, "IMOVEL", "IMOV");   
   
   private final Long id;
   private final String descricao; 
   private final String abreviado;

   TipoInfoCadeEnum(Long id, String descricao, String abreviado) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;      
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public static TipoInfoCadeEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (TipoInfoCadeEnum x : TipoInfoCadeEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}