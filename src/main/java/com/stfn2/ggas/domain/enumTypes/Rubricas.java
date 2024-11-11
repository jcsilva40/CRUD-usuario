package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum Rubricas {

   CONSUMO_GAS(1L, "Consumo de Gás", 23L),
   TRANSPORTE(2L, "Transporte", 25L),
   MARGEM_DISTRIBUICAO(3L, "Margem de Distribuição", 24L);

   private final Long id;
   private final String descricao;
   private final Long itemFatura;

   Rubricas(Long id, String descricao, Long itemFatura) {
      this.id = id;
      this.descricao=descricao;
      this.itemFatura = itemFatura;
   }

   public static Rubricas toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (Rubricas x : Rubricas.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}
