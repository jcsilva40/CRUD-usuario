package com.stfn2.ggas.domain.enumTypes;

import lombok.Getter;

@Getter
public enum PCSIntervaloEnum {

   REAL(1L, "REAL", "REAL", false, true),
   REDUZIDO(2L, "REDUZIDO", "RED", true, false),
   ESTENDIDO(3L, "ESTENDIDO", "EST", false, false);   

   private final Long id;
   private final String descricao;
   private final String abreviado;
   private final Boolean tamanho; //Só pode colocar a situação ao criar o contrato que esse atributo for true
   private final Boolean padrao;
   
   PCSIntervaloEnum(Long id, String descricao, String abreviado, Boolean tamanho, Boolean padrao) {
      this.id = id;
      this.descricao = descricao;
      this.abreviado = abreviado;
      this.tamanho = tamanho;
      this.padrao = padrao;
   }

   public static PCSIntervaloEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (PCSIntervaloEnum x : PCSIntervaloEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
}
