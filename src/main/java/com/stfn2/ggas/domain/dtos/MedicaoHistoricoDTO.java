package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.MedicaoHistorico;
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
public class MedicaoHistoricoDTO extends BaseDTO {
   private Long id;
   private Long pontoConsumoId;
   private String pontoConsumoDescricao;
   private Long medidorInstalacaoId;
   private BigDecimal leituraFaturada;
   private LocalDate dataLeituraFaturada;
   private BigDecimal leituraInformada;
   private Long anoMes;
   private Long ciclo;
   private BigDecimal leituraCorretor;
   private BigDecimal consumoCorretor;
   private BigDecimal consumoMedidor;
   private LocalDate dataLeituraInformada;
   private BigDecimal fatorPTZ;

   public MedicaoHistoricoDTO(MedicaoHistorico entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}