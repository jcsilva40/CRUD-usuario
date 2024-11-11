package com.stfn2.ggas.domain.enumTypes;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ClienteImovelFimRelacionamentoMotivoEnum {

   FIM_CONTRATO_ALUGUEL(1l, "Fim do Contrato de Aluguel"),
   VENDA_DO_IMOVEL(2l, "Venda do Imóvel"),
   FALENCIA(3l, "Falência"),
   ENCERRAMENTO_ATIVIDADES(4l, "Encerramento de Atividades"),;


   private Long id;
   private String descricao;

   private ClienteImovelFimRelacionamentoMotivoEnum(Long id, String descricao) {
      this.id = id;
      this.descricao=descricao;
   }

   @JsonValue
   public Long getId() {
      return id;
   }

   public String getDescricao() {
      return descricao;
   }

   public static ClienteImovelFimRelacionamentoMotivoEnum toEnum(Long id) {
      if(id==null) {
         return null;
      }

      for (ClienteImovelFimRelacionamentoMotivoEnum x : ClienteImovelFimRelacionamentoMotivoEnum.values()) {
         if(id.equals(x.getId())) {
            return x;
         }
      }
      throw new IllegalArgumentException("Id inválido: " + id);
   }

}
