package com.stfn2.ggas.services.componentes.vo;

import com.stfn2.ggas.domain.dtos.LiberacaoCronogramaFaturamentoDTO;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LiberacaoCronogramaVO implements Serializable  {
   @Serial
   private static final long serialVersionUID = 1L;

   private Long faturamentoGrupo;
   private String faturamentoGrupoDescricao;
   private Long faturamentoCronograma;
   private Integer anoMes;
   private Integer ciclo;
   private List<LiberacaoCronogramaFaturamentoDTO> etapas = new ArrayList<>();
   private List<FaturaLiberacaoVO> faturas = new ArrayList<>();
   private String statusCronograma;

   public void addEtapas(LiberacaoCronogramaFaturamentoDTO etapa) {
      this.etapas.add(etapa);
   }

   public void addFatura(FaturaLiberacaoVO fatura) {
      this.faturas.add(fatura);
   }

   public void addFatura(List<FaturaLiberacaoVO> faturas){
      this.faturas.addAll(faturas);
   }

}
