package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.FaturamentoGrupo;
import com.stfn2.ggas.domain.FaturamentoGrupoRotaImpress;
import com.stfn2.ggas.domain.Rota;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturamentoGrupoRotaImpressDTO extends BaseDTO {
   private Long id;
   private FaturamentoGrupo faturamentoGrupo;
   private Rota rota;
   private User usuario;
   private Integer anoMesReferencia;
   private LocalDateTime dataImpressao;
   private LocalDateTime dataUltimaImpressao;
   private Integer ciclo;
   private Integer sequenciaImpressao;

   public FaturamentoGrupoRotaImpressDTO(FaturamentoGrupoRotaImpress entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}