package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
/**
 * Referente a entidade classe número 7
 */
@NoArgsConstructor
public enum ItemFaturaEnum {

   TRANSPORTE(25L, "Transporte", ""),
   MARGEM_DE_DISTRIBUICAO(24L, "Margem de Distribuição", ""),
   GAS(23L, "Gás", "");

   private Long id;
   private String descricao;
   private String abreviado;

   ItemFaturaEnum(Long cod, String descricao, String abreviado) {
      this.id = cod;
      this.descricao = descricao;
      this.abreviado = abreviado;
   }

   @JsonValue
   public Long getCod() {
      return id;
   }

   public static ItemFaturaEnum toEnum(Long id) {
      if (id == null) {
         return null;
      }

      for (ItemFaturaEnum x : ItemFaturaEnum.values()) {
         if (id.equals(x.getCod())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}
