package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.config.security.domain.User;
import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FaturaImpressaoDTO extends BaseDTO {
   private Long id;
   private EntidadeConteudo tipoConvenio;
   private Fatura fatura;
   private FaturamentoAnormalidade faturamentoAnormalidade;
   private FaturamentoGrupo faturamentoGrupo;
   private Rota rota;
   private User usuario;
   private Integer anoMesReferencia = 0;
   private LocalDateTime dataGeracao;
   private LocalDateTime dataImpressao;
   private Boolean impressao;
   private Integer lote = 0;
   private Integer sequenciaImpressao = 0;
   private BigDecimal valorFatura = BigDecimal.ZERO;

   public FaturaImpressaoDTO(FaturaImpressao entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}