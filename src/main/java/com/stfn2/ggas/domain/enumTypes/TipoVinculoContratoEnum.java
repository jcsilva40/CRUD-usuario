package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TipoVinculoContratoEnum {

   PROPRIETARIO(1L, "PROPRIETÁRIO", ""),   
   INQUILINO(2L, "INQUILINO", "");
   
   private final Long id;
   private final String descricao; 
   private final String abreviado;

   TipoVinculoContratoEnum(Long id, String descricao, String abreviado) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;      
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public static TipoVinculoContratoEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (TipoVinculoContratoEnum x : TipoVinculoContratoEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}