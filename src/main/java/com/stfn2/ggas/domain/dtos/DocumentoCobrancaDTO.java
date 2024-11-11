package com.stfn2.ggas.domain.dtos;

import com.stfn2.ggas.core.interfaces.BaseDTO;
import com.stfn2.ggas.domain.Cliente;
import com.stfn2.ggas.domain.DocumentoCobranca;
import com.stfn2.ggas.domain.DocumentoTipo;
import com.stfn2.ggas.domain.enumTypes.CobrancaDebitoSituacaoEnum;
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
public class DocumentoCobrancaDTO extends BaseDTO {
   private Long id;
   private Cliente cliente;
   private DocumentoTipo documentoTipo;
   private CobrancaDebitoSituacaoEnum cobrancaDebitoSituacao;
   private LocalDate dataEmissao;
   private LocalDate dataVencimento;
   private Integer sequencial;
   private Integer sequencialImpressao;
   private Long nossoNumero;
   private BigDecimal valorTotal;
   private String codigoBarras;
   private String linhaDigitavel;

   public DocumentoCobrancaDTO(DocumentoCobranca entity) {
      super();
   }

   @Override
   public Long getId() {
      return this.id;
   }
}