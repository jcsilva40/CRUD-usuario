package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Cliente;
import com.stfn2.ggas.domain.Devolucao;
import com.stfn2.ggas.domain.EntidadeConteudo;
import com.stfn2.ggas.domain.PontoConsumo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DevolucaoDTO extends BaseDTO {
   private Long id;
   private EntidadeConteudo tipoDevolucao;
   private Cliente cliente;
   private PontoConsumo pontoConsumo;
   private LocalDateTime dataDevolucap;
   private String observacao;
   private BigDecimal valor = BigDecimal.ZERO;

   public DevolucaoDTO(Devolucao entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}