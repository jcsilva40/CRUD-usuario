package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.LiberacaoCronogramaFaturamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiberacaoCronogramaFaturamentoDTO extends BaseDTO {
   private Long id;
   private Long faturamentoGrupoId;
   private Long usuarioId;
   private String usuarioNome;
   private Integer anoMes;
   private Integer ciclo;
   private String etapa;
   private Boolean status;

   public LiberacaoCronogramaFaturamentoDTO(LiberacaoCronogramaFaturamento entity) {
     this.id = entity.getId();
      this.faturamentoGrupoId = entity.getFaturamentoGrupo().getId();
      if(entity.getUsuario() != null) {
         this.usuarioId = entity.getUsuario().getId();
         this.usuarioNome = entity.getUsuario().getNome();
      }
      this.anoMes = entity.getAnoMes();
      this.ciclo = entity.getCiclo();
      this.etapa = entity.getEtapa();
      this.status = entity.getStatus();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}
