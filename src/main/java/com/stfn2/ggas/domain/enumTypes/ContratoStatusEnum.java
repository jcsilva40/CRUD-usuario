package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ContratoStatusEnum {

   ATIVO_COM_GAS(1L, "ATIVO COM GÁS", ""),
   PENDENTE_LIBERACAO_GAS(2L, "PENDENTE LIBERAÇÃO GÁS", ""),
   INEXISTENTE(3L, "INEXISTENTE", "");
   
   private final Long id;
   private final String descricao; 
   private final String abreviado;

   ContratoStatusEnum(Long id, String descricao, String abreviado) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;      
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public static ContratoStatusEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (ContratoStatusEnum x : ContratoStatusEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}