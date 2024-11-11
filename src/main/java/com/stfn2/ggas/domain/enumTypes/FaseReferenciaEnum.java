package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
/**
 * Referente a entidade classe número 8
 */
public enum FaseReferenciaEnum {

   LEITURA(26L, "LEITURA", ""),
   FATURAMENTO(27L, "FATURAMENTO", ""),
   INICIO_MES(914L, "INICIO MÊS", "");
   
   private Long id;
   private String descricao;
   private String abreviado;

   FaseReferenciaEnum(Long id, String descricao, String abreviado) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;      
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public static FaseReferenciaEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (FaseReferenciaEnum x : FaseReferenciaEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}