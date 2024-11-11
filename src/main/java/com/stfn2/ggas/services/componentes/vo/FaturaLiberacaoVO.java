package com.stfn2.ggas.services.componentes.vo;

import com.stfn2.ggas.domain.Fatura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturaLiberacaoVO {

   private Long id;
   private Long segmentoId;
   private String pontoConsumoCil;
   private String pontoConsumoDescricao;
   private BigDecimal valorTotal;
   private LocalDate dataEmissao;
   private LocalDate dataVencimento;
   private String periodoCiclo;
   private Long statusFatura;

   public FaturaLiberacaoVO (Fatura entity) {
      this.id = entity.getId();
      this.segmentoId = entity.getSegmento().getId();
      this.pontoConsumoCil = entity.getPontoConsumo().getCil() ;
      this.pontoConsumoDescricao = entity.getPontoConsumo().getDescricao();
      this.valorTotal = entity.getValorTotal();
      this.dataEmissao = entity.getDataEmissao();
      this.dataVencimento = entity.getDataVencimento();
      this.periodoCiclo = entity.getPeriodoCiclo();
      this.statusFatura = entity.getStatusFatura().getId();
   }

}
