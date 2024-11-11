package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.FaturamentoGrupo;
import com.stfn2.ggas.domain.LeituraTipo;
import com.stfn2.ggas.domain.Periodicidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturamentoGrupoDTO extends BaseDTO {

   private Long id;
   private Periodicidade periodicidade;
   private LeituraTipo leituraTipo;
   private String descricao;
   private String abreviado;
   private Integer anoMesFaturamento;
   private Integer numeroCiclo;
   private Integer qtdMaxDiaClo;
   private Integer qtdMinDiaClo;
   private Integer diaVencimento;
   private Boolean vencimentoUltimoDiaCiclo;
   private Boolean cascataTarifaria;
   private Boolean vencimentoIgualFatura;

   public FaturamentoGrupoDTO(FaturamentoGrupo entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}
