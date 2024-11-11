package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.ModuloSistema;
import com.stfn2.ggas.domain.OperacaoSistema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperacaoSistemaDTO extends BaseDTO {
   private Long id;
   private Menu menu;
   private ModuloSistema moduloSistema;
   private String descricao;
   private Boolean auditavel = true;
   private Boolean exibir = true;
   private Integer tipoOperacao = 0;

   public OperacaoSistemaDTO(OperacaoSistema entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}