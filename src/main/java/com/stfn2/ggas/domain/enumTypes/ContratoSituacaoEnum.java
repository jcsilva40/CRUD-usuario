package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ContratoSituacaoEnum {

   ATIVO(1L, "ATIVO", true),
   ENCERRADO(2L, "ENCERRADO", false),
   CANCELADO(3L, "CANCELADO", false),
   ADITADO(4L, "ADITADO", false),
   EM_NEGOCIACAO(5L, "EM NEGOCIACAO", true),
   DISTRATADO(6L, "DISTRATADO", false),
   RESCINDIDO(7L, "RESCINDIDO", false),
   EM_CRIACAO(8L, "EM CRIACAO", true),
   ALTERADO(9L, "ALTERADO", false),
   EM_RECUPERACAO(10L, "EM RECUPERACAO", false),
   AGUARDANDO_APROVACAO(11L, "AGUARDANDO APROVACAO", false);

   private final Long id;
   private final String descricao;
   private final Boolean inclusaoInicial; //Só pode colocar a situação ao criar o contrato que esse atributo for true

   ContratoSituacaoEnum(Long id, String descricao, Boolean inclusaoInicial) {
      this.id = id;
      this.descricao = descricao;
      this.inclusaoInicial = inclusaoInicial;
   }

   @JsonCreator
   public static ContratoSituacaoEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (ContratoSituacaoEnum x : ContratoSituacaoEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }
   
   @JsonValue
   public Long getId() {
      return id;
   }
}
